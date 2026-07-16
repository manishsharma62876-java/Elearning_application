package com.elearning.service;

public interface EmailService {

    void sendEnrollmentEmail(
            String toEmail,
            String studentName,
            String courseName
    );

}