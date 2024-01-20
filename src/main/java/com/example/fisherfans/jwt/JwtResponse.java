package com.example.fisherfans.jwt;

import java.io.Serializable;

public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 1L;
    private String username;
    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

    public JwtResponse(String username, long id, String token) {
        super();
        this.username = username;
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }
}
