package com.springboot.PayLoads;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.springboot.Entity.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class UserDto {
	
	private int id;
	
	@NotNull
	@NotBlank
	@Size(min=5, message="Name must be minimum of 5 characters")
	private String name;
	
	@Email(message="Email address is not valid")
	private String email;
	
	//@NotNull+ @NotBlank= @NotEmpty.
	
	@NotEmpty(message="Password must not be Empty")
	@Size(min=5, max=10, message="Password must be min of 3 character and maximum of 10 character")
	
	private String password;
	
	//@NotNull
	//@NotBlank()
	@NotEmpty(message="About must not be Empty")
	
	private String about;
	
private List<RoleDto> roles=new ArrayList<>();
	
	public List<RoleDto> getRoles() {
	return roles;
}
public void setRoles(List<RoleDto> roles) {
	this.roles = roles;
}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public UserDto(int id, String name, String email, String password, String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "UserDto [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", about="
				+ about + "]";
	}

}
