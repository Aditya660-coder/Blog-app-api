package com.adityarai1.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adityarai1.blog.entities.Category;

public interface CategoryRepo extends JpaRepository<Category,Integer>{

}
