package com.ltw.blog.payload;

import javax.validation.constraints.*;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class UserDto {
	private int id;
	
	@NotEmpty
	@Size(min = 4, message = "Name must be min of 4 character!!")
	private String fullName;
	
	@NotEmpty
	@Size(min = 4, message = "Username must be min of 4 character!!")
	private String username;
	
	@Email(message = "Email address is not valid!!")
	private String email;
	
	@NotEmpty
	@Size(min= 3, max = 10, message = "Password must be min of 3 char and max of 10 char")
	private String password;
	
	@NotEmpty
	private String about;
}
