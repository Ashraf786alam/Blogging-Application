package com.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {

}
