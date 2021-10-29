package com.demo.studentSecurity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.studentSecurity.dto.RequestDto;
import com.demo.studentSecurity.entity.Project;
import com.demo.studentSecurity.entity.Student;
import com.demo.studentSecurity.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;

	public Student save(RequestDto dto) {

		Student std = new Student(dto.getStudentId(),dto.getFirstName(),dto.getLastName(),dto.getEmail(),dto.getMobileNo());
		
		String [] projects = dto.getProjects().split(",");
				
		for(int i=0; i < projects.length; i++) {
			std.add(new Project(projects[i]));
		}
		
		return studentRepository.save(std);
		
	}

	public List<Student> findByStudentId(int studentId) {
		return studentRepository.findByStudentId(studentId);
	}

	public List<Student> findAll() {
		return studentRepository.findAll();
	}
	
	

}
