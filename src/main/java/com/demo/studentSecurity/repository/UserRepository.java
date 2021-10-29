package com.demo.studentSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.studentSecurity.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	public Users findByUserName(String username);

}
