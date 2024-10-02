package com.ecommerce.services.auth;

import com.ecommerce.dto.SignupRequest;
import com.ecommerce.dto.UserDto;
import com.ecommerce.entitiy.User;

public interface AuthService {
	
	UserDto  createUser(SignupRequest signupRequest);

	boolean hasUserWithEmail(String email);
	
	User getUserById(Long id);
	
	boolean resetPassword(String email, String newPassword);

}
