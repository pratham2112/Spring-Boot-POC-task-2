package com.demo.studentSecurity.dto;

import lombok.Data;

@Data
public class RequestDto {
	
	private int studentId;
	
	private String firstName;
	
	private String lastName;
	
	private String email;
	
	private int mobileNo;
	
	private String projects;
	

}
