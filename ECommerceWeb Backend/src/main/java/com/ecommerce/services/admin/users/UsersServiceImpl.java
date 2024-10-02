package com.ecommerce.services.admin.users;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.dto.UserDto;
import com.ecommerce.entitiy.User;
import com.ecommerce.enums.UserRole;
import com.ecommerce.repository.UserRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UsersServiceImpl  implements UsersService{
	
	
	
	@Autowired
	private UserRepository userRepository;
	
	
	public List<UserDto> getAllUsers()
	
	{
		List<User> users = userRepository.findAll();
		
		return users.stream()
	            .filter(user -> user.getRole() == UserRole.CUSTOMER)
	            .map(User::getDto)
	            .collect(Collectors.toList());
	}

}
