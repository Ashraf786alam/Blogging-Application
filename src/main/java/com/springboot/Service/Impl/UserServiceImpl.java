package com.springboot.Service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.Config.AppConstants;
import com.springboot.Entity.Role;
import com.springboot.Entity.User;
import com.springboot.Exception.ResourceNotFoundException;
import com.springboot.PayLoads.UserDto;
import com.springboot.Repository.RoleRepository;
import com.springboot.Repository.UserRepo;
import com.springboot.Service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepo userRepo; 
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDto createUser(UserDto user) {
		
		return this.UserToDto(this.userRepo.save(this.dtoToUser(user)));
	}

	@Override
	public UserDto updateUser(UserDto dtouser, int userId) {
		
		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," Id ",userId));
		user.setId(userId);
		user.setName(dtouser.getName());
		user.setEmail(dtouser.getEmail());
		user.setPassword(dtouser.getPassword());
		user.setAbout(dtouser.getAbout());
		User updatedUser=this.userRepo.save(user);
		return this.UserToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(int userId) {

		User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","Id",userId));
        return this.UserToDto(user);
		
	}

	@Override
	public List<UserDto> getAllUsers() {
    
      List<User> users=this.userRepo.findAll();
      
         List<UserDto> userdtos=users.stream().map((user)->this.UserToDto(user)).collect(Collectors.toList());
		return userdtos;
	}

	@Override
	public void deleteUser(int userId) {

     User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," Id ",userId));
     this.userRepo.delete(user);
	}
	
	private User dtoToUser(UserDto dto) {
		
		User user=this.modelMapper.map(dto,User.class);
//		user.setId(dto.getId());
//		user.setName(dto.getName());
//		user.setEmail(dto.getEmail());
//		user.setPassword(dto.getPassword());
//		user.setAbout(dto.getAbout());
		return user;
	}
    
    private UserDto UserToDto(User user) {
		
		UserDto userdto=this.modelMapper.map(user, UserDto.class);
		
		return userdto;
	}

	@Override
	public UserDto RegisterNewUser(UserDto userdto) {

      User user=this.modelMapper.map(userdto,User.class);
      
      //encode the password.
      user.setPassword(this.passwordEncoder.encode(user.getPassword()));
      
      //set the roles.
      Role role=this.roleRepo.findById(AppConstants.NORMAL_USER).get();
      user.getRoles().add(role);
      User newuser=this.userRepo.save(user);
      
		return this.modelMapper.map(newuser, UserDto.class);
	}

}
