package com.ecommerce.services.customer.profile;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.ecommerce.dto.UserDto;

import com.ecommerce.entitiy.User;
import com.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerProfileServiceImpl  implements CustomerProfileService
{
	
	 @Autowired
	 private UserRepository userRepository;
	 
	 
 
	 
	 public User updateUserProfile(String email, UserDto userDto) throws IOException {
		    Optional<User> optionalUser = userRepository.findFirstByEmail(email);

		    if (optionalUser.isPresent()) {
		        User user = optionalUser.get();

		        user.setName(userDto.getName());
		        user.setMobile(userDto.getMobile());
		        user.setGender(userDto.getGender());

		        // Check if a new image file is provided
		        if (userDto.getImg() != null && !userDto.getImg().isEmpty()) {
		            user.setImg(userDto.getImg().getBytes()); // Convert MultipartFile to byte[]
		        } else if (userDto.getByteImg() != null) {
		            user.setImg(userDto.getByteImg()); // Keep existing image if no new file is provided
		        }

		        return userRepository.save(user);
		    } else {
		        throw new RuntimeException("User not found");
		    }

}
}
