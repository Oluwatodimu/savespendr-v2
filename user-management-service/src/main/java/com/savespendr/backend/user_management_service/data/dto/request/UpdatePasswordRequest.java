package com.savespendr.backend.user_management_service.data.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

public class UpdatePasswordRequest {

    @NotNull(message = "password field cannot be null")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[\\W_]).+$")
    private String password;

    @NotNull(message = "confirm password field cannot be null")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[\\W_]).+$")
    private String confirmPassword;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
