package com.springboot.Service.Impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.Entity.Category;
import com.springboot.Entity.Post;
import com.springboot.Entity.User;
import com.springboot.Exception.ResourceNotFoundException;
import com.springboot.PayLoads.PostDto;
import com.springboot.PayLoads.PostResponse;
import com.springboot.Repository.CategoryRepository;
import com.springboot.Repository.PostRepository;
import com.springboot.Repository.UserRepo;
import com.springboot.Service.PostService;

@Service
public class PostServiceImpl implements PostService{
	
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	
	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public PostDto createPost(PostDto postdto,int userId,int categoryId) {
		
		Post post=this.modelMapper.map(postdto,Post.class);
		User user=this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," Id ",userId));
		
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category"," Id",categoryId));
		post.setImageName("Default.png");
		post.setDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		Post newPost= this.postRepo.save(post);
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto UpdatePost(PostDto postdto, int postId) {

		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post"," Id ",postId));
        
        post.setTitle(postdto.getTitle());
        post.setContent(postdto.getContent());
        post.setDate(postdto.getDate());
        post.setImageName(postdto.getImageName());
        User user=post.getUser();
        Category category=post.getCategory();
        post.setUser(this.modelMapper.map(postdto.getUser(), User.class));
        post.setCategory(this.modelMapper.map(postdto.getCategory(),Category.class));
        post.getUser().setId(user.getId());
        post.getCategory().setCategoryId(category.getCategoryId());
        Post updatedPost=this.postRepo.save(post);
        //post.getUser().setId(postdto.getUser().getId());
        this.userRepo.save(post.getUser());
        this.categoryRepo.save(post.getCategory());
		return this.modelMapper.map(updatedPost,PostDto.class);
	}

	@Override
	public void deletePost(int postId) {

    Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post"," Id ",postId));
	this.postRepo.delete(post);
	}

	@Override
	public PostDto getPostById(int postId) {
		Post post=this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post"," Id",postId));
		
		return this.modelMapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPost(int pageNumber,int pageSize,String sortBy,String sortDir) {
		
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")) {
			sort=Sort.by(sortBy).ascending();
		}
		
		if(sortDir.equalsIgnoreCase("desc")) {
			sort=Sort.by(sortBy).descending();
		}
		
		Pageable pageable=PageRequest.of(pageNumber, pageSize,sort);
		Page<Post> pagePost=this.postRepo.findAll(pageable);
		List<Post> all_posts=pagePost.getContent();
		List<PostDto> postdtos=all_posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		
		PostResponse postresponse=new PostResponse();
		postresponse.setContent(postdtos);
		postresponse.setPageNumber(pagePost.getNumber());
		postresponse.setPageSize(pagePost.getSize());
		postresponse.setTotalElements(pagePost.getTotalElements());
		postresponse.setTotalPages(pagePost.getTotalPages());
		postresponse.setLastPage(pagePost.isLast());
		postresponse.setFirstPage(pagePost.isFirst());
		return postresponse;
	}

	@Override
	public List<PostDto> getAllPostByCategory(int categoryId) {
		Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category"," Id ",categoryId));
		List<Post> category_posts=this.postRepo.findByCategory(category);
		return category_posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> getAllPostByUser(int userId) {

        User user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User"," Id ",userId));
		List<Post> user_posts=this.postRepo.findByUser(user);
        return user_posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
		List<Post> posts=this.postRepo.SearchByTitle("%"+keyword+"%");
		List<PostDto> postdtos=posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return postdtos;
	}

	@Override
	public List<PostDto> searchByTitleStartingWith(String keyword) {

          List<Post> posts=this.postRepo.SearchByTitleStartingWith(keyword+"%");
          List<PostDto> postdtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdtos;
	}

	@Override
	public List<PostDto> searchContentContaining(String keyword) {

          List<Post> posts=this.postRepo.SearchByContent("%"+keyword+"%");
          
		return posts.stream().map((post)->this.modelMapper.map(post,PostDto.class)).collect(Collectors.toList());
				
	}

}
