package com.savespendr.backend.user_management_service.data.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

public class UserSignupRequest {

    @NotNull(message = "first name cannot be null")
    private String firstName;

    @NotNull(message = "last name cannot be null")
    private String lastName;

    @NotNull(message = "password cannot be null")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[\\W_]).+$")
    private String password;

    @NotNull(message = "username cannot be null")
    private String username;

    @Email
    @NotNull(message = "email cannot be null")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
