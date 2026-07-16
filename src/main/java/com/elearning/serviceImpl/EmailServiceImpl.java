package com.elearning.serviceImpl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.elearning.service.EmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEnrollmentEmail(
            String toEmail,
            String studentName,
            String courseName) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(toEmail);

        message.setSubject("Course Enrollment Successful");

        message.setText(
                "Hello " + studentName +
                "\n\nYou have successfully enrolled in:\n\n"
                + courseName
                + "\n\nHappy Learning!"
        );

        mailSender.send(message);
    }

}