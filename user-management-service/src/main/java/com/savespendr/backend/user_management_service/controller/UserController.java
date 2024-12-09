package com.savespendr.backend.user_management_service.controller;

import com.savespendr.backend.user_management_service.data.dto.request.UpdatePasswordRequest;
import com.savespendr.backend.user_management_service.data.dto.request.UserSignupRequest;
import com.savespendr.backend.user_management_service.data.dto.response.BaseResponse;
import com.savespendr.backend.user_management_service.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

//@Slf4j
@Validated
@RestController
@RequestMapping(path = "/api/v2/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/user-signup")
    public ResponseEntity<BaseResponse> createUser(@RequestBody @Valid UserSignupRequest request) {
//        log.info("creating new user with username: {}", request.getUsername());
        userService.registerNormalUser(request);
        return new ResponseEntity<>(new BaseResponse("user created successfully", false, null), HttpStatus.CREATED);
    }

    @PutMapping(path = "/forgot-password/{username}")
    public ResponseEntity<BaseResponse> resetPassword(@PathVariable(name = "username") String username) {
//        log.info("resetting password flow initiated for user with username: {}", username);
        userService.resetPassword(username);
        return new ResponseEntity<>(new BaseResponse("password reset link sent to email", false, null), HttpStatus.OK);
    }

    @PutMapping(path = "/update-password")
    @PreAuthorize("hasRole('update_password')")
    public ResponseEntity<BaseResponse> updatePassword(Principal principal, @RequestBody @Valid UpdatePasswordRequest request) {
//        log.info("updating password flow initiated for user with username: {}", principal.getName());
        userService.updatePassword(principal.getName(), request);
        return ResponseEntity.ok(new BaseResponse("password update link sent to email", false, null));
    }
}
