package com.adityarai1.blog.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adityarai1.blog.entities.Comment;

public interface CommentRepo  extends JpaRepository<Comment,Integer>
{

}
