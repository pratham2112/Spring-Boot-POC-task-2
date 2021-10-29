package com.demo.studentSecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.studentSecurity.dto.AuthRequest;
import com.demo.studentSecurity.util.JwtUtil;

@RestController
@RequestMapping("/wel")
public class WelcomeController {
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@GetMapping("/come")
	public String welcome() {
		
		return "Welcome to Prathamesh World";
		
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

}
