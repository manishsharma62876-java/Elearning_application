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

        try {

            SimpleMailMessage message = new SimpleMailMessage();

            // Sender Email
            message.setFrom("manishsharma62876@gmail.com");

            // Receiver Email
            message.setTo(toEmail);

            // Subject
            message.setSubject("🎉 Course Enrollment Successful");

            // Email Body
            message.setText(

                    "Dear " + studentName + ",\n\n"

                    + "Congratulations!\n\n"

                    + "You have successfully enrolled in the following course:\n\n"

                    + "Course Name : " + courseName + "\n\n"

                    + "We wish you a successful learning journey.\n\n"

                    + "Happy Learning!\n\n"

                    + "Regards,\n"

                    + "E-Learning Team"

            );

            mailSender.send(message);

            System.out.println("====================================");
            System.out.println("Email Sent Successfully");
            System.out.println("To : " + toEmail);
            System.out.println("Course : " + courseName);
            System.out.println("====================================");

        } catch (Exception e) {

            System.out.println("====================================");
            System.out.println("Email Sending Failed");
            System.out.println(e.getMessage());
            System.out.println("====================================");

            e.printStackTrace();
        }

    }

}