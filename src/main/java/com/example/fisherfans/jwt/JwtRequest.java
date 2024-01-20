package com.example.fisherfans.jwt;

import java.io.Serializable;

public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 2636936156391265891L;
    private String username;
    private String password;
    private String token;

    public JwtRequest() {
    }

    public JwtRequest(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public JwtRequest(String username, String password, String token) {
        super();
        this.username = username;
        this.password = password;
        this.token    = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Object getSession() {
        return null;
    }
}
