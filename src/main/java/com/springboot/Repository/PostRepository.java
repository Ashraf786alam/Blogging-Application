package com.springboot.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.springboot.Entity.Category;
import com.springboot.Entity.Post;
import com.springboot.Entity.User;
import com.springboot.PayLoads.PostDto;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer>{
	
	public List<Post> findByUser(User user);
	public List<Post> findByCategory(Category category);
	
	@Query("select p from Post p where p.title like: key")
	public List<Post> SearchByTitle(@Param("key") String title);
	
	@Query("select p from Post p where p.title like :key")
	public List<Post> SearchByTitleStartingWith(@Param("key") String title);
	
	@Query("select p from Post p where p.content like :key")
	public List<Post> SearchByContent(@Param("key") String content);

}
