package com.demo.studentSecurity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.studentSecurity.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	List<Student> findByStudentId(int studentId);

}
