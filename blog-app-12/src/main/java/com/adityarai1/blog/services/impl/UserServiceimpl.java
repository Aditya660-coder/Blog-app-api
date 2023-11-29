package com.adityarai1.blog.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adityarai1.blog.entities.User;
import com.adityarai1.blog.exceptions.ResourceNotFoundException;
import com.adityarai1.blog.payloads.UserDto;
import com.adityarai1.blog.repositories.UserRepo;
import com.adityarai1.blog.services.UserService;
@Service
public class UserServiceimpl implements UserService {
	
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto create(UserDto userDto) {
		
		User user = this.dtoTouser(userDto);
		User savedUser = this.userRepo.save(user);
		
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto update(UserDto userDto, Integer userId) 
	{
	    User user1 = this.userRepo.findById(userId).
	    		orElseThrow(()-> new ResourceNotFoundException("User","id",userId));
		
	    
	    user1.setName(userDto.getName());
	    user1.setAbout(userDto.getAbout());
	    user1.setEmail(userDto.getEmail());
	    user1.setAbout(userDto.getAbout());
	    
	    User updateUser = this.userRepo.save(user1);
	    UserDto userDto1 = this.userToDto(updateUser);
	    
	    
		return userDto1;
	}

	@Override
	public UserDto getUserById(Integer userId) 
	{
	  User user = this.userRepo.findById(userId)
			  .orElseThrow(()->new ResourceNotFoundException("User", "Id", userId));
	  
		
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllusers() 
	{
        List<User> users = this.userRepo.findAll();
        
		List<UserDto> userDtos=users.stream().map(user->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {

		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "Id", userId));
		this.userRepo.delete(user);
	}
	public User dtoTouser(UserDto userDto)
	{
		User user =this.modelMapper.map(userDto,User.class);
		
		// user.setId(userDto.getId());
		// user.setName(userDto.getName());
		// user.setEmail(userDto.getEmail());
		// user.setAbout(userDto.getAbout());
		// user.setPassword(userDto.getPassword());
		
		return user;
		
	}
	
	public UserDto userToDto(User user)
	{
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		// UserDto userDto= new userDto;
		//userDto.setId(user.getId());
		//userDto.setName(user.getName());
		//userDto.setEmail(user.getEmail());
		//userDto.setAbout(user.getAbout());
		//userDto.setPassword(user.getPassword());
		return userDto;
	}
	

}
