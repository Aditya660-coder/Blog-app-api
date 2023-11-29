package com.adityarai1.blog.services;

import java.util.List;

import com.adityarai1.blog.payloads.UserDto;

public interface UserService 
{
     UserDto  create(UserDto user );
     UserDto update(UserDto user, Integer userId);
     UserDto getUserById(Integer userId);
     List<UserDto> getAllusers();
     void deleteUser(Integer usereId);
}
