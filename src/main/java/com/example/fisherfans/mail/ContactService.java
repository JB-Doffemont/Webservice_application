package com.example.fisherfans.mail;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import jakarta.mail.MessagingException;

@Service
public class ContactService {

    private final GmailAPIService gmailAPIService;

    public ContactService(GmailAPIService gmailAPIService) {
        this.gmailAPIService = gmailAPIService;
    }

    public void submitContactRequest(String subject, String description, String to) {
        try {
            gmailAPIService.sendMessage(subject, description, to);
        } catch (MessagingException | IOException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not able to process request.");
        }
    }
}
