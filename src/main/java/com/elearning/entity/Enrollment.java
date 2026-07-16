package com.elearning.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "enrollments")
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private User student;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	private LocalDateTime enrolledDate;

	private String status;

	@PrePersist
	public void onCreate() {

		this.enrolledDate = LocalDateTime.now();

		this.status = "ACTIVE";

	}

}