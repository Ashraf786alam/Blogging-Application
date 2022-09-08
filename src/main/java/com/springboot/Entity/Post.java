package com.springboot.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Post")
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int postId;
	@Column(name="post_title",nullable=false,length=100)
	private String title;
	
	@Column(name="post_content",nullable=false,length=200)
	private String content;
	
	private String imageName;
	
	private Date date;
	
	@OneToMany(mappedBy="post",cascade=CascadeType.ALL)
	private List<Comment> comments;
	
	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		return "Post [postId=" + postId + ", title=" + title + ", content=" + content + ", imageName=" + imageName
				+ ", date=" + date + ", user=" + user + ", category=" + category + "]";
	}

	public Post() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Post(int postId, String title, String content, String imageName, Date date, User user, Category category) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.imageName = imageName;
		this.date = date;
		this.user = user;
		this.category = category;
	}

	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@ManyToOne()
	private User user;
	
	@ManyToOne()
	@JoinColumn(name="category_id")
	private Category category;

}
