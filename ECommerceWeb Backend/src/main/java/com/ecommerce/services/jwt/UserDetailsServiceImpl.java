package com.ecommerce.services.jwt;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ecommerce.entitiy.User;
import com.ecommerce.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
	
	    @Autowired
	    private UserRepository userRepository;


	    @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	    {

	        Optional<User> optionaluser = userRepository.findFirstByEmail(username);
	        if(optionaluser.isEmpty()) throw new UsernameNotFoundException("Username not found",null);
	      
	        return new org.springframework.security.core.userdetails.User(optionaluser.get().getEmail(), optionaluser.get().getPassword(), new ArrayList<>());
	    }

}
