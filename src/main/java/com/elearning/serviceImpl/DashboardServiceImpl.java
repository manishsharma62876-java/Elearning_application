package com.elearning.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.elearning.dto.response.AdminDashboardResponse;
import com.elearning.dto.response.EnrollmentResponse;
import com.elearning.dto.response.StudentDashboardResponse;
import com.elearning.entity.Enrollment;
import com.elearning.entity.Role;
import com.elearning.entity.User;
import com.elearning.exception.ResourceNotFoundException;
import com.elearning.mapper.EnrollmentMapper;
import com.elearning.repository.CourseRepository;
import com.elearning.repository.EnrollmentRepository;
import com.elearning.repository.UserRepository;
import com.elearning.service.DashboardService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;

    private final CourseRepository courseRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final EnrollmentMapper enrollmentMapper;

    @Override
    public AdminDashboardResponse getAdminDashboard() {

        AdminDashboardResponse response = new AdminDashboardResponse();

        // Total Students
        response.setTotalStudents(
                userRepository.countByRole(Role.STUDENT));

        // Total Courses
        response.setTotalCourses(
                courseRepository.count());

        // Total Enrollments
        response.setTotalEnrollments(
                enrollmentRepository.count());

        return response;
    }

    @Override
    public StudentDashboardResponse getStudentDashboard(String email) {

        // Find Student
        User student = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));

        // Get Student Enrollments
        List<Enrollment> enrollments =
                enrollmentRepository.findByStudent(student);

        // Convert Entity -> DTO
        List<EnrollmentResponse> courseList = enrollments.stream()
                .map(enrollmentMapper::toResponse)
                .toList();

        // Prepare Dashboard Response
        StudentDashboardResponse response =
                new StudentDashboardResponse();

        response.setStudentName(student.getName());

        response.setTotalEnrolledCourses(
                (long) courseList.size());

        response.setMyCourses(courseList);

        return response;
    }

}