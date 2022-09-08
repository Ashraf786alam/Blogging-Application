package com.springboot.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springboot.PayLoads.UserDto;


public interface UserService {
	
	public UserDto RegisterNewUser(UserDto userdto);
	
	  public UserDto createUser(UserDto user);
	  
	  public UserDto updateUser(UserDto user,int userId);
	  
	  public UserDto getUserById(int userId);
	  
	  public List<UserDto> getAllUsers();
	  
	  void deleteUser(int userId);

}
