package com.ltw.blog.payload;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.ltw.blog.entities.Post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter

public class CategoryDto {

	private Integer id;
	@NotBlank
	@Size(min = 4, message = "Title toi thieu 4 ky tu!!!" )
	private String title;
	
	@NotBlank
	@Size(max = 10, message = "Description toi da 10 ky tu!!!" )
	private String description;
	
}
