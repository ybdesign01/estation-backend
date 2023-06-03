package com.app.estation.dto;

public class UserEmailRequest {

    private String email;

    public UserEmailRequest() {
    }

    public UserEmailRequest(final String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }
}
