package com.hansung.web.model;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Board{ 
	private int boardNum;
	
	@NotEmpty(message="The Product name must not be null")
	private String title;
	
	@NotEmpty(message="The Product name must not be null")
	private String text;
	
	private String writer;
	
	private String date;
	
	private int count;
	
	private int password;

}


