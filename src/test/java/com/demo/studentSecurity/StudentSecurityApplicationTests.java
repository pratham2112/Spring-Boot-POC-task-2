package com.demo.studentSecurity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.studentSecurity.dto.RequestDto;
import com.demo.studentSecurity.entity.Project;
import com.demo.studentSecurity.entity.Student;
import com.demo.studentSecurity.repository.StudentRepository;
import com.demo.studentSecurity.service.StudentService;

@RunWith(SpringRunner.class)
@SpringBootTest
class StudentSecurityApplicationTests {

	@Autowired
	private StudentService studentService;
	
	@MockBean
	private StudentRepository studentRepository;
	
	@Test
	public void findAllTest() {
		
		when(studentRepository.findAll()).thenReturn(Stream.of(new Student(101,"Pratham","Patle","pratham@gmail.com",98899)
															  ,new Student(102,"Sayee","Bhogle","sayee@gmail.com",900303)
															  ).collect(Collectors.toList()));
		
		assertEquals(2, studentService.findAll().size());
		
	}
	
	@Test
	public void findByStudentIdTest() {
		
		int id = 101;
		
		when(studentRepository.findByStudentId(id)).thenReturn(Stream.of(new Student(101,"Pratham","Patle","pratham@gmail.com",98899)
				  												).collect(Collectors.toList()));
		
	}
	
	@Test
	public void saveStudentTest() {
		
		
		Student std = new Student(101,"Pratham","Patle","pratham@gmail.com",98899);
		
		when(studentRepository.save(std)).thenReturn(std);
		
		assertEquals(std, studentRepository.save(std));
		
	}

}
