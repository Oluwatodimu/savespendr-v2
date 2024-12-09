package com.savespendr.backend.user_management_service.data.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdatePasswordRequest {

    @NotNull(message = "password field cannot be null")
    private String password;

    @NotNull(message = "confirm password field cannot be null")
    private String confirmPassword;
}
