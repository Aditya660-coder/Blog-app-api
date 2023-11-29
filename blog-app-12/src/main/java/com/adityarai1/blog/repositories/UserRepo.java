 package com.adityarai1.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adityarai1.blog.entities.User;

public interface  UserRepo extends JpaRepository<User, Integer> 
{
  
}
