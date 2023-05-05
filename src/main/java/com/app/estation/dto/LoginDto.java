package com.app.estation.dto;

import com.app.estation.advice.validation.InsertValidation;
import com.app.estation.advice.validation.UpdateValidation;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class LoginDto {
    @NotBlank(message = "email_mandatory")
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "email_invalid")
    private String email;
    @NotBlank(message = "password_mandatory")
    @Length(min = 8, max = 20, message = "password_length", groups = {InsertValidation.class, UpdateValidation.class})
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$", message = "password_regex")
    private String password;


    public LoginDto() {
    }

    public LoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
