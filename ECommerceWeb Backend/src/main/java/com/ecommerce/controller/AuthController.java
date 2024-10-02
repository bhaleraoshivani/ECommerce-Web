package com.ecommerce.controller;

import java.io.IOException;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dto.AuthenticationRequest;
import com.ecommerce.dto.ForgotPasswordRequestDto;
import com.ecommerce.dto.SignupRequest;
import com.ecommerce.dto.UserDto;
import com.ecommerce.entitiy.User;
import com.ecommerce.repository.UserRepository;
import com.ecommerce.services.auth.AuthService;
import com.ecommerce.utils.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController 
{
	
	 @Autowired
	 private  JwtUtil jwtUtil;

	 @Autowired
	 private  AuthenticationManager authenticationManager;

	 @Autowired
	 private  UserDetailsService userDetailsService;
	 
	 @Autowired
	 private  UserRepository userRepository;
	 
	 @Autowired
	 private  AuthService authService;
	 
	 public static final String HEADER_STRING = "Authorization";
	 
	 public static final String TOKEN_PREFIX = "Bearer ";
	 
	 
	 
	 @PostMapping("/authenticate")
	 public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException, JSONException 
	 {
	        try 
	        {
	            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));
	        }
	        catch (BadCredentialsException e) 
	        {
	            throw new BadCredentialsException("Incorrect username or password!");
	        } 
	        

	        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
            Optional<User> optionalUser = userRepository.findFirstByEmail(userDetails.getUsername());
	        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
	        
	        if(optionalUser.isPresent())
	        {
	        	response.getWriter().write(new JSONObject()
	        			.put("userId",optionalUser.get().getId())
	        			.put("role",optionalUser.get().getRole())
	        			.toString()
	        			);
	        	
	        	
	        	response.addHeader("Access-Control-Expose-Headers","Authorization");
	        	response.addHeader("Access-Control-Allow-Headers","Authorization ,X-PINGOTHER ,Origin," + 
	        	"X-Requested-With,Content-Type,Accept,X-Custom-header");
	        	response.addHeader(HEADER_STRING,TOKEN_PREFIX + jwt);
	      
	        }       

     }

	 
	 @PostMapping("/sign-up")
     public  ResponseEntity<?> signupUser(@RequestBody SignupRequest signupRequest)
     {
    	 if(authService.hasUserWithEmail(signupRequest.getEmail()))
    	 {
    		 return new ResponseEntity<>("User Already Exists",HttpStatus.NOT_ACCEPTABLE);
    	 }
    	 
    	 UserDto userDto = authService.createUser(signupRequest);
    	 return new  ResponseEntity<>(userDto,HttpStatus.OK);
     }
	 
	 
	 

	 @PostMapping("/forgot-password")
	 public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequestDto request) {
	     boolean success = authService.resetPassword(request.getEmail(), request.getNewPassword());
	     if (success) {
	         return ResponseEntity.ok("Password reset successful");
	     } else {
	         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error resetting password");
	     }
	 }
	
	 
}
