package com.elearning.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.elearning.dto.request.EnrollmentRequest;
import com.elearning.dto.response.EnrollmentResponse;
import com.elearning.service.EnrollmentService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {


    private final EnrollmentService enrollmentService;



    // ===============================
    // STUDENT - Enroll Course
    // ===============================

    @PostMapping("/add")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<EnrollmentResponse> enrollCourse(

            @RequestBody EnrollmentRequest request,
            Authentication authentication

    ){

        String email = authentication.getName();

        return ResponseEntity.ok(
                enrollmentService.enrollCourse(request, email)
        );

    }




    // ===============================
    // STUDENT - My Courses
    // ===============================

    @GetMapping("/my-courses")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<List<EnrollmentResponse>> getMyCourses(

            Authentication authentication

    ){

        String email = authentication.getName();

        return ResponseEntity.ok(
                enrollmentService.getMyCourses(email)
        );

    }





    // ===============================
    // ADMIN - View All Enrollments
    // ===============================

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<EnrollmentResponse>> getAllEnrollments(){

        return ResponseEntity.ok(
                enrollmentService.getAllEnrollments()
        );

    }


}