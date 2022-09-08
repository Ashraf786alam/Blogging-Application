package com.springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.Entity.Comment;

public interface CommentRepo extends JpaRepository<Comment,Integer> {

}
