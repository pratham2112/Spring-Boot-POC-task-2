package com.demo.studentSecurity.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="student")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="student_id")
	private int studentId;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="mobile_no")
	private int mobileNo;
	
	@OneToMany(mappedBy = "student", cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
	@JsonIgnoreProperties("student")
	private List<Project> projects;

	
	public void add(Project tempProj) {
		
		if(projects == null) {
			
			projects = new ArrayList<>();
			
		}
		
		projects.add(tempProj);
		
		tempProj.setStudent(this);
		
	}


	public Student(int studentId,String firstName, String lastName, String email, int mobileNo) {
		//super();
		this.studentId = studentId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobileNo = mobileNo;
	}
	
	

}
