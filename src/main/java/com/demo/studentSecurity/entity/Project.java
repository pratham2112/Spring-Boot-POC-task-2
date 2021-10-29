package com.demo.studentSecurity.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name="project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="project_id")
	private int projectId;
	
	@Column(name="project_name")
	private String projectName;
	
	@Column(name="duration")
	private int duration;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.ALL})
	@JoinColumn(name="studentId")
	@JsonIgnoreProperties("projects")
	private Student student;

	public Project(String projectName) {
		this.projectName = projectName;
	}

	
}
