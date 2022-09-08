package com.springboot.Service.Impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.Entity.Comment;
import com.springboot.Entity.Post;
import com.springboot.Exception.ResourceNotFoundException;
import com.springboot.PayLoads.CommentDto;
import com.springboot.Repository.CommentRepo;
import com.springboot.Repository.PostRepository;
import com.springboot.Service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{
   
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;
	@Override
	public CommentDto createComment(CommentDto commentdto, int postId) {
		
		Post post=this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," Id",postId));
		Comment comment=this.modelMapper.map(commentdto,Comment.class);
		comment.setPost(post);
		Comment savedComment=this.commentRepo.save(comment);
		
		return this.modelMapper.map(savedComment,CommentDto.class);
	}

	@Override
	public void deleteComment(int commentId) {
		Comment  comment=this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment"," Id",commentId));
		this.commentRepo.delete(comment);
		
	}

}
