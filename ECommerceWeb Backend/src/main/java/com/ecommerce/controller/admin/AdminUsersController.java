package com.ecommerce.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.UserDto;
import com.ecommerce.services.admin.users.UsersService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminUsersController {
	
	
	@Autowired
	private UsersService usersService;
	
	
	
	@GetMapping("/users")
	public ResponseEntity<List<UserDto>> getAllUsers()
	{
		List<UserDto> userDto = usersService.getAllUsers();
	    return ResponseEntity.ok(userDto);
	}

}
