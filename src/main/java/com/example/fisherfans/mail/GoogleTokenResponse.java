package com.example.fisherfans.mail;

import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.util.Key;

public class GoogleTokenResponse extends TokenResponse {

  @Key("expires_in")
  private Integer expiresInSeconds;

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null || getClass() != obj.getClass()) {
      return false;
    }
    GoogleTokenResponse other = (GoogleTokenResponse) obj;
    return expiresInSeconds.equals(other.expiresInSeconds);
  }

  @Override
  public int hashCode() {
    return expiresInSeconds.hashCode();
  }
  
}