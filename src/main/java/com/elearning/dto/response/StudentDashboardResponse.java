package com.elearning.dto.response;

import java.util.List;

import lombok.Data;

@Data
public class StudentDashboardResponse {

    private String studentName;

    private Long totalEnrolledCourses;

    private List<EnrollmentResponse> myCourses;

}