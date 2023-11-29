package com.adityarai1.blog.payloads;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.adityarai1.blog.entities.Category;
import com.adityarai1.blog.entities.Comment;
import com.adityarai1.blog.entities.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class PostDto {


	private Integer postId;
	private String title;
	
	private String content;
	
	private String imageName;
	
	private Date addDate;
	
	private UserDto user;
	
	private CategoryDto category;
	
	//we can access through it posts
	private Set<CommentDto> comments = new HashSet<>();
	
	
}
