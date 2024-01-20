package com.example.fisherfans.mail;

import org.springframework.http.HttpStatus;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.auth.oauth2.BearerToken;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.gmail.*;
import com.google.api.services.gmail.model.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.Properties;

import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public class GmailAPIService {

    private HttpTransport httpTransport;

    private GmailCredential gmailCredential;

    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();

    private String clientId;

    private String secretKey;

    private String refreshToken;

    private String fromEmail;

    private String emailApi;

    public GmailAPIService() throws GeneralSecurityException, IOException {
        this.httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        new GmailCredential(
                clientId,
                secretKey,
                refreshToken,
                "refresh_token",
                null,
                null);

    }

    public boolean sendMessage(String subject, String body, String to) throws MessagingException, IOException {
        Credential credential = authorize();
        Message message = createMessageWithEmail(createEmail(to, fromEmail, subject, body));
        Gmail gmail = new Gmail.Builder(httpTransport, JSON_FACTORY, credential).build();
        return gmail.users().messages().send(gmailCredential.userEmail(), message).execute().getLabelIds()
                .contains("SENT");
    }

    private Message createMessageWithEmail(MimeMessage emailContent) throws MessagingException, IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        emailContent.writeTo(buffer);
        byte[] messageBytes = buffer.toByteArray();
        String encodedEmail = Base64.getUrlEncoder().encodeToString(messageBytes);
        return new Message().setRaw(encodedEmail);
    }

    private MimeMessage createEmail(String to, String from, String subject, String bodyText) throws MessagingException {
        MimeMessage email = new MimeMessage(Session.getDefaultInstance(new Properties(), null));
        email.setFrom(new InternetAddress(from));
        email.addRecipient(RecipientType.TO, new InternetAddress(to));
        email.setSubject(subject);
        email.setText(bodyText);
        return email;
    }

    private Credential authorize() {
        try {
            TokenResponse tokenResponse = refreshAccessToken();
            return new Credential(BearerToken.authorizationHeaderAccessMethod()).setFromTokenResponse(tokenResponse);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not able to process request.");
        }
    }

    private TokenResponse refreshAccessToken() throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();
        GmailCredential gmailCredentialsDto = new GmailCredential(
                clientId,
                secretKey,
                refreshToken,
                "refresh_token",
                null,
                null);

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(gmailCredentialsDto);

        try {
            GoogleTokenResponse response = restTemplate.postForObject(emailApi, json, GoogleTokenResponse.class);
            if (response != null) {
                gmailCredential = new GmailCredential(clientId, secretKey, refreshToken, null,
                        response.getAccessToken(),
                        fromEmail);
                return response;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while refreshing access token.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error while refreshing access token.");
        }
    }
}