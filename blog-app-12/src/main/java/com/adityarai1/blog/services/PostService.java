package com.adityarai1.blog.services;

import java.util.List;

import com.adityarai1.blog.entities.Post;
import com.adityarai1.blog.payloads.PostDto;
import com.adityarai1.blog.payloads.PostResponse;

public interface PostService 
{

	// create 
	
	PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
	
	//update 
	
	PostDto updatePost(PostDto postDto, Integer postId);
	
	// delete
	void deletePost(Integer postId);
	
	//get all post
	PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy);
	
	//get single Post
	PostDto getPostById(Integer postId);
	
	//get all post by category
	
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//get all post By User
	
	List<PostDto> getPostsByUser(Integer userId);
	
	//search post using the keywords
	List<PostDto> searchPost(String keyword);
	
	
}
