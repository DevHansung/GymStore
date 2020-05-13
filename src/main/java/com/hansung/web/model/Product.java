package com.hansung.web.model;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class Product{ 
	private static final long serialVersionUID = -567117648902464025L;

	private int productid;
	
	@NotEmpty(message="The Product name must not be null")
	private String name;
	
	private String category;
	
	@Min(value=0, message="The Product must not be less than zero ")
	private int price;
	
	@NotEmpty(message="The Product manufacturer must not be null")
	private String manufacturer;
	
	@Min(value=0, message="The Product unitInStock must not be less than zero ")
	private int unitInStock;
	
	private String description;
	
	private MultipartFile productImage;
	
	private String imageFilename;
	
}


