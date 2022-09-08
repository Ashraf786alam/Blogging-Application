package com.springboot.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.Entity.User;
import com.springboot.Exception.ResourceNotFoundException;
import com.springboot.Repository.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
	@Autowired
	private UserRepo userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user=this.userRepo.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User"," Email",username));

		return user;
	}
	

}
