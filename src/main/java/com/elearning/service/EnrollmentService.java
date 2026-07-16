package com.elearning.service;

import java.util.List;

import com.elearning.dto.request.EnrollmentRequest;
import com.elearning.dto.response.EnrollmentResponse;

public interface EnrollmentService {

	EnrollmentResponse enrollCourse(EnrollmentRequest request, String email);

	List<EnrollmentResponse> getMyCourses(String email);

}