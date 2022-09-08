package com.springboot.Service;

import java.util.List;

import com.springboot.Entity.Category;
import com.springboot.Entity.Post;
import com.springboot.Entity.User;
import com.springboot.PayLoads.PostDto;
import com.springboot.PayLoads.PostResponse;

public interface PostService {
	
	public PostDto createPost(PostDto postdto,int userId,int categoryId);
	
	public PostDto UpdatePost(PostDto postdto,int postId);
	
	public void deletePost(int postId);
	
	public PostDto getPostById(int postId);
	
	public PostResponse getAllPost(int pageNumber,int pageSize,String sortBy,String sortDir);
	
	public List<PostDto> getAllPostByCategory(int categoryId);
	
	public List<PostDto> getAllPostByUser(int userId);
	
	public List<PostDto> searchPost(String keyword);
	
	public List<PostDto> searchByTitleStartingWith(String keyword);
	
	public List<PostDto> searchContentContaining(String keyword);

}
