package com.springboot;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.springboot.Config.AppConstants;
import com.springboot.Entity.Role;
import com.springboot.Repository.RoleRepository;

@SpringBootApplication
public class BloggingApplication implements CommandLineRunner {
	
	@Autowired
	private PasswordEncoder passwordencoder;
	
	@Autowired
	private RoleRepository roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BloggingApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
		
	}

	@Override
	public void run(String... args) throws Exception {
		
      System.out.println(this.passwordencoder.encode("ramkapoor@123"));
      System.out.println(this.passwordencoder.encode("ravisihag@123"));
      try {
    	 Role role1=new Role(); 
    	 role1.setRoleId(AppConstants.NORMAL_USER);
    	 role1.setRoleName("ROLE_NORMAL");
    	 
    	 Role role2=new Role(); 
    	 role2.setRoleId(AppConstants.ADMIN_USER);
    	 role2.setRoleName("ROLE_ADMIN");
    	 
    	 List<Role> roles=List.of(role1,role2);
    	// roles.add(role1);
    	// roles.add(role2);
    	 this.roleRepo.saveAll(roles);
    	 
      }
      
      catch(Exception e) {
    	  
      }
	}

}
