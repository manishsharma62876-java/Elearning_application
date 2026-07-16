package com.elearning.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.elearning.dto.response.AdminDashboardResponse;
import com.elearning.dto.response.StudentDashboardResponse;
import com.elearning.service.DashboardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    // ===========================
    // ADMIN DASHBOARD
    // ===========================
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AdminDashboardResponse> getAdminDashboard() {

        AdminDashboardResponse response =
                dashboardService.getAdminDashboard();

        return ResponseEntity.ok(response);
    }

    // ===========================
    // STUDENT DASHBOARD
    // ===========================
    @GetMapping("/student")
    @PreAuthorize("hasRole('STUDENT')")
    public ResponseEntity<StudentDashboardResponse> getStudentDashboard(
            Authentication authentication) {

        String email = authentication.getName();

        StudentDashboardResponse response =
                dashboardService.getStudentDashboard(email);

        return ResponseEntity.ok(response);
    }

}