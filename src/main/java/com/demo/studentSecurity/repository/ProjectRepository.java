package com.demo.studentSecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.studentSecurity.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
