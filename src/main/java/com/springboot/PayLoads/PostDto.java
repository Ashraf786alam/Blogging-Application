package com.springboot.PayLoads;

import java.util.Date;
import java.util.List;

import com.springboot.Entity.Category;
import com.springboot.Entity.Comment;
import com.springboot.Entity.User;

public class PostDto {
	
	private int postId;
	
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date date;
	
	private UserDto user;
	
	private CategoryDto category;
	
	private List<CommentDto> comments;
	
	public List<CommentDto> getComments() {
		return comments;
	}

	public void setComments(List<CommentDto> comments) {
		this.comments = comments;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CategoryDto getCategory() {
		return category;
	}

	public void setCategory(CategoryDto category) {
		this.category = category;
	}

	public PostDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	
	public PostDto(int postId, String title, String content, String imageName, Date date, UserDto user,
			CategoryDto category, List<CommentDto> comments) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.date = date;
		this.user = user;
		this.category = category;
		this.comments = comments;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	

}
