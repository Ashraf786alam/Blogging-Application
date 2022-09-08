package com.springboot.Controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.Entity.User;
import com.springboot.PayLoads.UserDto;
import com.springboot.Service.UserService;

@RestController
@RequestMapping("/api/users/")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/")
	public ResponseEntity<UserDto> CreateUser(@Valid @RequestBody UserDto userdto){
		
		//return ResponseEntity.status(HttpStatus.OK).body(this.userService.createUser(userdto));
		
		UserDto userdto1=this.userService.createUser(userdto);
		return new ResponseEntity<>(userdto1,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> UpdateUser(@Valid @RequestBody UserDto userdto, @PathVariable("userId") int id){
		
		UserDto updateduser=this.userService.updateUser(userdto, id);
		return new ResponseEntity<>(updateduser,HttpStatus.OK);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("delete/{userId}")
	public ResponseEntity<?> DeleteUser(@PathVariable int userId){
		
		this.userService.deleteUser(userId);
		//return ResponseEntity.ok(Map.of("Message","User Deleted Successfully"));
		return new ResponseEntity<>(Map.of("Message","User Deleted Successfully with Id "+userId),HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> GetUsers(){
		
		return new ResponseEntity<>(this.userService.getAllUsers(),HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> getSingleUser(@PathVariable int userId){
		
		return new ResponseEntity<>(this.userService.getUserById(userId),HttpStatus.OK);
	}

}
