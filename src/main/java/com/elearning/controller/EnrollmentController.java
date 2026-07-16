package com.elearning.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.dto.request.EnrollmentRequest;
import com.elearning.dto.response.EnrollmentResponse;
import com.elearning.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

	private final EnrollmentService enrollmentService;

// Student Enroll Course

	@PostMapping("/add")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<EnrollmentResponse> enrollCourse(

			@RequestBody EnrollmentRequest request,

			Authentication authentication) {

		String email = authentication.getName();

		return ResponseEntity.ok(

				enrollmentService.enrollCourse(request, email)

		);

	}

// Student Dashboard - My Courses

	
	@GetMapping("/my-courses")
	@PreAuthorize("hasRole('STUDENT')")
	public ResponseEntity<List<EnrollmentResponse>> getMyCourses(

			Authentication authentication) {

		String email = authentication.getName();

		return ResponseEntity.ok(

				enrollmentService.getMyCourses(email)

		);

	}

}