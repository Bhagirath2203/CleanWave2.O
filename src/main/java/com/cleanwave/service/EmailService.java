package com.cleanwave.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendComplaintSubmittedEmail(String to, String complaintId) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Complaint Submitted - CleanWave");
            message.setText("Your complaint has been submitted successfully.\n\n" +
                    "Complaint ID: " + complaintId + "\n\n" +
                    "We will review your complaint and take appropriate action.\n\n" +
                    "Thank you for using CleanWave!");
            
            mailSender.send(message);
        } catch (Exception e) {
            // Log error but don't fail the operation
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
    
    public void sendComplaintAssignedEmail(String to, String complaintId, String workerName) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Complaint Assigned - CleanWave");
            message.setText("Your complaint (ID: " + complaintId + ") has been assigned to " + workerName + ".\n\n" +
                    "They will work on resolving your issue soon.\n\n" +
                    "Thank you for your patience!");
            
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
    
    public void sendComplaintStatusUpdateEmail(String to, String complaintId, String status) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Complaint Status Updated - CleanWave");
            message.setText("Your complaint (ID: " + complaintId + ") status has been updated to: " + status + "\n\n" +
                    "Thank you for using CleanWave!");
            
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
    
    public void sendWorkerAssignmentEmail(String to, String complaintId, String category) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("New Complaint Assigned - CleanWave");
            message.setText("A new complaint has been assigned to you.\n\n" +
                    "Complaint ID: " + complaintId + "\n" +
                    "Category: " + category + "\n\n" +
                    "Please login to view details and update the status.");
            
            mailSender.send(message);
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}
