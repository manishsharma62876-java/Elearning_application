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
import com.elearning.service.EmailService;
import com.elearning.service.EnrollmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    private final CourseRepository courseRepository;

    private final UserRepository userRepository;

    private final EnrollmentMapper enrollmentMapper;

    private final EmailService emailService;

    @Override
    public EnrollmentResponse enrollCourse(
            EnrollmentRequest request,
            String email) {

        // Find Student

        User student = userRepository.findByEmail(email)

                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));



        // Find Course

        Course course = courseRepository.findById(request.getCourseId())

                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found"));



        // Check Duplicate Enrollment

        boolean alreadyEnrolled =
                enrollmentRepository.existsByStudentIdAndCourseId(
                        student.getId(),
                        course.getId()
                );

        if (alreadyEnrolled) {

            throw new RuntimeException(
                    "You are already enrolled in this course."
            );

        }



        // Create Enrollment

        Enrollment enrollment = new Enrollment();

        enrollment.setStudent(student);

        enrollment.setCourse(course);



        // Save Enrollment

        Enrollment savedEnrollment =
                enrollmentRepository.save(enrollment);



        // Send Confirmation Email

        try {

            emailService.sendEnrollmentEmail(

                    student.getEmail(),

                    student.getName(),

                    course.getTitle()

            );

            System.out.println(
                    "Enrollment email sent successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "Email sending failed: " + e.getMessage()
            );

        }



        // Return Response

        return enrollmentMapper.toResponse(savedEnrollment);

    }



    @Override
    public List<EnrollmentResponse> getMyCourses(
            String email) {

        User student = userRepository.findByEmail(email)

                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found"));



        List<Enrollment> enrollments =
                enrollmentRepository.findByStudent(student);



        return enrollments

                .stream()

                .map(enrollmentMapper::toResponse)

                .toList();

    }
    @Override
    public List<EnrollmentResponse> getAllEnrollments(){

        return enrollmentRepository.findAll()
                .stream()
                .map(enrollmentMapper::toResponse)
                .toList();

    }

}