package com.elearning.service;

import com.elearning.dto.response.AdminDashboardResponse;
import com.elearning.dto.response.StudentDashboardResponse;

public interface DashboardService {

    AdminDashboardResponse getAdminDashboard();

    StudentDashboardResponse getStudentDashboard(String email);
    
}
