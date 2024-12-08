package com.savespendr.backend.user_management_service.data.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
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

}
