package com.demo.studentSecurity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.studentSecurity.dto.AuthRequest;
import com.demo.studentSecurity.dto.RequestDto;
import com.demo.studentSecurity.entity.Student;
import com.demo.studentSecurity.service.StudentService;
import com.demo.studentSecurity.util.JwtUtil;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private StudentService studentService;
	
	
	@RequestMapping("/getMsg")
	public String getMsg() {
		return "Spring Security";
	}
	
	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		
		try{
			authenticationManager.authenticate(
		
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(),authRequest.getPassword())
				);
		}catch(Exception e) {
			throw new Exception("Invalid Username or Password");
		}
		
		return jwtUtil.generateToken(authRequest.getUserName());
		
	}
	
	@PostMapping("/addStudent")
	public Student addStudent(@RequestBody RequestDto dto){
		
		return studentService.save(dto);
		
	}
	
	@GetMapping("/getStudents/{studentId}")
	public List<Student> getStudentsById(@PathVariable int studentId){
		
		return studentService.findByStudentId(studentId);
		
	}
	
	@GetMapping("/getAllStudents")
	public List<Student> getAllStudents(){
		
		return studentService.findAll();
		
	}

}
