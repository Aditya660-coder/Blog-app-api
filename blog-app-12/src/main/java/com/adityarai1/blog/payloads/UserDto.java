package com.adityarai1.blog.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserDto 
{
   private int id ;
   
    @NotEmpty
    @Size(min=4, message ="Username must be of 4 characters or more")
   private String name;
   
   @Email(message ="email address is not valid")
   private  String email;
	
	@NotEmpty
	@Size(min =3, max = 10, message = "Password must be in between 3 to 10 characters")
	private String password;
	
	@NotEmpty
	private String about;
}
