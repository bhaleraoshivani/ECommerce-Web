package com.ecommerce.controller.customer;

import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ecommerce.dto.UserDto;
import com.ecommerce.entitiy.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.services.customer.profile.CustomerProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProfileContoller 
{
	
	 @Autowired
	 private UserRepository userRepository;
	 
	 @Autowired
	 private CustomerProfileService customerProfileService;
	 
	
	 
	 @GetMapping("/profile")
	 public ResponseEntity<?> getUserProfile(Principal principal) 
	 {
	     Optional<User> optionalUser = userRepository.findFirstByEmail(principal.getName());
	     if (optionalUser.isPresent()) {
	         User user = optionalUser.get();
	         UserDto userDto = new UserDto(user.getId(), user.getEmail(),user.getName(),user.getMobile(),user.getGender(), user.getImg());
	         return new ResponseEntity<>(userDto, HttpStatus.OK);
	     }
	     return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
	 }
	 
	
	 @PutMapping("/profile/update")
	 public ResponseEntity<UserDto> updateProfile(Principal principal, @ModelAttribute UserDto userDto) throws IOException {
	     String email = principal.getName();
	     User updatedUser = customerProfileService.updateUserProfile(email, userDto);
	     
	     if (updatedUser != null) {
	         UserDto updatedUserDto = new UserDto(updatedUser.getId(), updatedUser.getEmail(), updatedUser.getName(), updatedUser.getMobile(), updatedUser.getGender(),updatedUser.getImg());
	         return ResponseEntity.ok(updatedUserDto);
	     } else {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	     }
	 }

}
