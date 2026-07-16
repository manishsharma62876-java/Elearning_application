package com.elearning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elearning.entity.Enrollment;
import com.elearning.entity.User;

public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {

	List<Enrollment> findByStudent(User student);
	
	boolean existsByStudentIdAndCourseId(
	        Long studentId,
	        Long courseId
	);
}
