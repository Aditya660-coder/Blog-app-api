package com.adityarai1.blog.services.impl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.adityarai1.blog.entities.Category;
import com.adityarai1.blog.entities.Post;
import com.adityarai1.blog.entities.User;
import com.adityarai1.blog.exceptions.ResourceNotFoundException;
import com.adityarai1.blog.payloads.PostDto;
import com.adityarai1.blog.payloads.PostResponse;
import com.adityarai1.blog.repositories.CategoryRepo;
import com.adityarai1.blog.repositories.PostRepo;
import com.adityarai1.blog.repositories.UserRepo;
import com.adityarai1.blog.services.PostService;


@Service
public class PostServiceImpl implements PostService {

	
	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;

	
	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		
		User user = this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("user", "userId", userId));
		Category category = this.categoryRepo.findById(categoryId).orElseThrow(
				()->new ResourceNotFoundException("category", "category id",categoryId));
		Post post = this.modelMapper.map(postDto,Post.class);
		post.setImagename("default.png");
		post.setAddDate(new Date());
		post.setUser(user);
		post.setCategory(category);
		
		Post newPost = this.postRepo.save(post);
		return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId) {
		Post post =this.postRepo.findById(postId).orElseThrow(
				()->new ResourceNotFoundException("post", "post id", postId));
		  post.setTitle(postDto.getTitle());
		  post.setContent(postDto.getContent());
		  post.setImagename(postDto.getImageName());
		  Post updatePost = this.postRepo.save(post);
		  return this.modelMapper.map(updatePost, PostDto.class);
		
	}

	@Override
	public void deletePost(Integer postId) {
	  Post post =this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "post id", postId));
      this.postRepo.delete(post);
	}

	@Override
	public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy) {
		
	Pageable p  = PageRequest.of(pageNumber,pageSize,Sort.by(sortBy));
	
	Page<Post>pagePost = this.postRepo.findAll(p);
	List<Post>allPosts = pagePost.getContent();
	List<PostDto>allPostdtos= allPosts.stream().map(
			(post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
	
	PostResponse postResponse = new PostResponse();
	postResponse.setContent(allPostdtos);
	postResponse.setPageNumber(pagePost.getNumber());
	postResponse.setPageSize(pagePost.getSize());
	postResponse.setTotalElements(pagePost.getTotalElements());
	postResponse.setTotalPages(pagePost.getTotalPages());
	postResponse.setLastPage(pagePost.isLast());
	
	return postResponse;
	}

	@Override
	public PostDto getPostById(Integer postId) 
	{
		Post post =this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("post", "post id", postId));
		return this.modelMapper.map(post, PostDto.class);
	
	}
	@Override
	public List<PostDto> getPostsByCategory(Integer categoryId) 
	{
		Category cat = this.categoryRepo.findById(categoryId).orElseThrow(
				()->new ResourceNotFoundException("category", "category id",categoryId));
		List<Post>posts = this.postRepo.findByCategory(cat);
		List<PostDto>postdtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdtos;
	}

	@Override
	public List<PostDto> getPostsByUser(Integer userId) 
	{
		User user = this.userRepo.findById(userId).orElseThrow(
				()->new ResourceNotFoundException("user", "user id", userId));
		List<Post>posts = this.postRepo.findByUser(user);
		List<PostDto>postdtos = posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdtos;
	}

	@Override
	public List<PostDto> searchPost(String keyword) {
      
		List<Post>posts=this.postRepo.findByTitleContaining(keyword);
		List<PostDto>postdtos=posts.stream().map((post)->this.modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		return postdtos;
	}

}
