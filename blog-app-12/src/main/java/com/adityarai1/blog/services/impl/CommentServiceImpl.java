package com.adityarai1.blog.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adityarai1.blog.entities.Comment;
import com.adityarai1.blog.entities.Post;
import com.adityarai1.blog.exceptions.ResourceNotFoundException;
import com.adityarai1.blog.payloads.CommentDto;
import com.adityarai1.blog.repositories.CommentRepo;
import com.adityarai1.blog.repositories.PostRepo;
import com.adityarai1.blog.services.CommentService;
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private PostRepo postRepo;
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId) {
		 Post post =this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "post id", postId));
		 Comment comment = this.modelMapper.map(commentDto, Comment.class);
		 comment.setPost(post);
		 Comment savedComment = this.commentRepo.save(comment);
		return this.modelMapper.map(savedComment,CommentDto.class);
	}

	@Override
	public void deleteComment(Integer commentId) {
	
	Comment com = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "comment id", commentId));
	this.commentRepo.delete(com);	

	}

}
