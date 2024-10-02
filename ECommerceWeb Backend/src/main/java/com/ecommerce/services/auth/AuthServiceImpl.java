package com.ecommerce.services.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.SignupRequest;
import com.ecommerce.dto.UserDto;
import com.ecommerce.entitiy.Order;
import com.ecommerce.entitiy.User;
import com.ecommerce.enums.OrderStatus;
import com.ecommerce.enums.UserRole;
import com.ecommerce.repository.OrderRepository;
import com.ecommerce.repository.UserRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService
{
	
	 @Autowired
	 private  UserRepository userRepository;
	 
	 @Autowired
	 private OrderRepository orderRepository;
	 
	 @Autowired
	 private BCryptPasswordEncoder passwordEncoder;
	 
	 
	 
	 public UserDto  createUser(SignupRequest signupRequest)
	 {
		 // This is for create new user
		 User user = new User();
		 
		 user.setEmail(signupRequest.getEmail());
		 user.setName(signupRequest.getName());
		 user.setMobile(signupRequest.getMobile());
		 user.setPassword(new BCryptPasswordEncoder().encode(signupRequest.getPassword()));
		 user.setRole(UserRole.CUSTOMER);
		 User createUser = userRepository.save(user);
		 
		 // This is for create cart 
		 Order order  = new Order();
		 order.setAmount(0L);
		 order.setTotalAmount(0L);
		 order.setDiscount(0L);
		 order.setUser(createUser);
		 order.setOrderStatus(OrderStatus.Pending);
		 orderRepository.save(order);
		 
		 UserDto userDto = new UserDto();
		 userDto.setId(createUser.getId());
		 
		 return userDto;
	 }


	@Override
	public boolean hasUserWithEmail(String email) {
		// TODO Auto-generated method stub
		return userRepository.findFirstByEmail(email).isPresent();
	}
	
	
	@PostConstruct
	public void createAdminAccount()
	{
		User adminAccount = userRepository.findByRole(UserRole.ADMIN);
		
		if(null==adminAccount)
		{
			 User user = new User();
			 
			 user.setEmail("admin@test.com");
			 user.setName("admin");
	         user.setMobile("1234567891");
			 user.setRole(UserRole.ADMIN);
			 user.setPassword(new BCryptPasswordEncoder().encode("admin"));
			 userRepository.save(user);
			
		}
	}
	
	
	 public User getUserById(Long id) 
	 {
	     return userRepository.findById(id).orElse(null);
	 }
	 
	 
	 
	 public boolean resetPassword(String email, String newPassword) {
	        User user = userRepository.findFirstByEmail(email).orElse(null);
	        if (user != null) {
	            user.setPassword(passwordEncoder.encode(newPassword));
	            userRepository.save(user);
	            return true;
	        }
	        return false;
	    }

}
