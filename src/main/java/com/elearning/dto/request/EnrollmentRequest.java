package com.elearning.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class EnrollmentRequest {

	private Long courseId;
	
}
