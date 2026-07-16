package com.elearning.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentResponse {

	  private Long id;


	    private String courseName;


	    private String category;


	    private LocalDateTime enrolledDate;


	    private String status;
	    
}
