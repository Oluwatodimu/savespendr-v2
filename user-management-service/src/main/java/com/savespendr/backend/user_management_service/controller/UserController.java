package com.savespendr.backend.user_management_service.controller;

import com.savespendr.backend.user_management_service.data.dto.request.UserSignupRequest;
import com.savespendr.backend.user_management_service.data.dto.response.BaseResponse;
import com.savespendr.backend.user_management_service.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user-signup")
    public ResponseEntity<BaseResponse> createUser(@RequestBody @Valid UserSignupRequest request) {
        userService.registerNormalUser(request);
        return new ResponseEntity<>(new BaseResponse("user created successfully", false, null), HttpStatus.CREATED);
    }
}
