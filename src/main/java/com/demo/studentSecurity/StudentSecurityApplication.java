package com.demo.studentSecurity;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.demo.studentSecurity.entity.Users;
import com.demo.studentSecurity.repository.UserRepository;

@SpringBootApplication
public class StudentSecurityApplication {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostConstruct
	public void initUser() {
		
		List<Users> users = Stream.of(new Users(101,"admin","admin","admin", true),
									 new Users(102,"student","student","student",true)
									 ).collect(Collectors.toList());
		userRepository.saveAll(users);
	}

	public static void main(String[] args) {
		SpringApplication.run(StudentSecurityApplication.class, args);
	}

}
