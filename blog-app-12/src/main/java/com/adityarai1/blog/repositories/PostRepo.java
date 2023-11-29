package com.adityarai1.blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adityarai1.blog.entities.Category;
import com.adityarai1.blog.entities.Post;
import com.adityarai1.blog.entities.User;

public interface  PostRepo  extends JpaRepository<Post,Integer> 
{
   
	List<Post>findByUser(User user);// custom find methods
	List<Post>findByCategory(Category category);
	
	List<Post>findByTitleContaining(String title);
}
