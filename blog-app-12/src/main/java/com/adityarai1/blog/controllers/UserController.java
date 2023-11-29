package com.adityarai1.blog.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adityarai1.blog.payloads.ApiResponse;
import com.adityarai1.blog.payloads.UserDto;
import com.adityarai1.blog.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController 
{
	@Autowired
     private UserService userService;
	// Post - create
	// Put - update user
	// delete - delete user 
	// Get - user get
     
@PostMapping("/")

       public ResponseEntity<UserDto> create(@Valid @RequestBody UserDto userDto)
      {
	    
	    UserDto createUserDto = this.userService.create(userDto);
	    return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
	
      }
// Put -update user

@PutMapping("/{userId}")
       public ResponseEntity<UserDto> update(@Valid @RequestBody UserDto userDto, @PathVariable("userId") Integer uid )
       {
	       UserDto updatedUser = this.userService.update(userDto, uid);
	       return ResponseEntity.ok(updatedUser);
       }
@DeleteMapping("/{userId}")   
     public ResponseEntity<ApiResponse> deleteUser( @PathVariable("userId") Integer uid )
     {
       this.userService.deleteUser(uid);
       return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted", true), HttpStatus.OK);
     }

// Get all Users

@GetMapping("/")
          public ResponseEntity<List<UserDto>> getAllUsers()
         {
           return ResponseEntity.ok(this.userService.getAllusers());
         }


@GetMapping("/{userId}")
         public ResponseEntity<UserDto> getSingleUser(@PathVariable("userId") Integer userid)
        {
         return ResponseEntity.ok(this.userService.getUserById(userid));
        }

}
