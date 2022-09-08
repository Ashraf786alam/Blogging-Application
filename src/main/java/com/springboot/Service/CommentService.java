package com.springboot.Service;

import com.springboot.PayLoads.CommentDto;

public interface CommentService {
	
	public CommentDto createComment(CommentDto commentdto,int postId);
	
	public void deleteComment(int commentId);
	

}
