package com.elearning.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elearning.dto.request.EnrollmentRequest;
import com.elearning.dto.response.EnrollmentResponse;
import com.elearning.entity.Course;
import com.elearning.entity.Enrollment;
import com.elearning.entity.User;
import com.elearning.exception.ResourceNotFoundException;
import com.elearning.mapper.EnrollmentMapper;
import com.elearning.repository.CourseRepository;
import com.elearning.repository.EnrollmentRepository;
import com.elearning.repository.UserRepository;
import com.elearning.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

	private final EnrollmentRepository enrollmentRepository;

	private final CourseRepository courseRepository;

	private final UserRepository userRepository;

	private final EnrollmentMapper enrollmentMapper;

	@Override
	public EnrollmentResponse enrollCourse(EnrollmentRequest request, String email) {

		// 1. Find Student using JWT email

		User student = userRepository.findByEmail(email)

				.orElseThrow(() -> new ResourceNotFoundException("Student not found"));

		// 2. Find Course

		Course course = courseRepository.findById(request.getCourseId())

				.orElseThrow(() -> new ResourceNotFoundException("Course not found"));

		// 3. Check duplicate enrollment

		boolean alreadyEnrolled = enrollmentRepository.existsByStudentIdAndCourseId(student.getId(), course.getId());

		if (alreadyEnrolled) {

			throw new RuntimeException("Already enrolled in this course");

		}

		// 4. Create Enrollment Object

		Enrollment enrollment = new Enrollment();

		enrollment.setStudent(student);

		enrollment.setCourse(course);

		// status and date automatically handled by @PrePersist

		// 5. Save database

		Enrollment savedEnrollment = enrollmentRepository.save(enrollment);

		// 6. Return Response DTO

		return enrollmentMapper.toResponse(savedEnrollment);

	}

	@Override
	public List<EnrollmentResponse> getMyCourses(String email) {

		// Find Student

		User student = userRepository.findByEmail(email)

				.orElseThrow(() -> new ResourceNotFoundException("Student not found"));

		// Fetch Enrollment List

		List<Enrollment> enrollments = enrollmentRepository.findByStudent(student);

		// Entity -> DTO

		return enrollments

				.stream()

				.map(enrollmentMapper::toResponse)

				.toList();

	}

}