package com.elearning.mapper;

import org.springframework.stereotype.Component;

import com.elearning.dto.response.EnrollmentResponse;
import com.elearning.entity.Enrollment;

@Component
public class EnrollmentMapper {

	public EnrollmentResponse toResponse(Enrollment enrollment) {

		EnrollmentResponse response = new EnrollmentResponse();

		response.setId(enrollment.getId());

		response.setCourseName(enrollment.getCourse().getTitle());

		response.setCategory(enrollment.getCourse().getCategory());

		response.setEnrolledDate(enrollment.getEnrolledDate());

		response.setStatus(enrollment.getStatus());

		return response;

	}

}