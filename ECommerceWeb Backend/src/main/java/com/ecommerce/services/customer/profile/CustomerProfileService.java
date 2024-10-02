package com.ecommerce.services.customer.profile;

import java.io.IOException;

import com.ecommerce.dto.UserDto;
import com.ecommerce.entitiy.User;

public interface CustomerProfileService {
	
	User updateUserProfile(String email, UserDto userDto) throws IOException;
	 
	// UserDto getProfileById(Long userId);

}
