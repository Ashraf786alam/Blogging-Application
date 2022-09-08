package com.springboot.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Exception.InvalidCredentialsException;
import com.springboot.PayLoads.JwtAuthRequest;
import com.springboot.PayLoads.JwtAuthResponse;
import com.springboot.PayLoads.UserDto;
import com.springboot.Security.JwtTokenHelper;
import com.springboot.Service.UserService;



@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) throws Exception{
		
		this.authenticate(request.getUsername(),request.getPassword());
		UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
		
		String token=this.jwtTokenHelper.genrateToken(userDetails);
		
		JwtAuthResponse jwtauthresponse=new JwtAuthResponse();
		jwtauthresponse.setToken(token);
		return new ResponseEntity<JwtAuthResponse>(jwtauthresponse,HttpStatus.OK);
		
		
		
	}

	private void authenticate(String username, String password) throws Exception {
         
		 UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);
          try {
        	  this.authenticationManager.authenticate(authenticationToken);
          }
          catch(BadCredentialsException e) {
        	  //System.out.println("Invalid Details.");
        	  throw new InvalidCredentialsException("Invalid Username and Password");
          }
	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDto> RegisterUser(@RequestBody UserDto userdto){
		UserDto registeredUser=this.userService.RegisterNewUser(userdto);
		return new ResponseEntity<UserDto>(registeredUser,HttpStatus.OK);
		
	}

}
