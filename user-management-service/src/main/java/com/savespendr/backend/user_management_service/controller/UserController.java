package com.savespendr.backend.user_management_service.controller;

import com.savespendr.backend.user_management_service.data.dto.request.UpdatePasswordRequest;
import com.savespendr.backend.user_management_service.data.dto.request.UserSignupRequest;
import com.savespendr.backend.user_management_service.data.dto.response.BaseResponse;
import com.savespendr.backend.user_management_service.service.UserService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Validated
@RestController
@RequestMapping(path = "/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/user-signup")
    public ResponseEntity<BaseResponse> createUser(@RequestBody @Valid UserSignupRequest request) {
        log.info("creating new user with username: {}", request.getUsername());
        userService.registerNormalUser(request);
        return new ResponseEntity<>(new BaseResponse("user created successfully", false, null), HttpStatus.CREATED);
    }

    @PostMapping(path = "/merchant-signup")
    @PreAuthorize("hasRole('create_merchant')")
    public ResponseEntity<BaseResponse> createMerchantUser(@RequestBody @Valid UserSignupRequest request) {
        log.info("creating new merchant with username: {}", request.getUsername());
        String userId = userService.registerMerchantUser(request);
        return new ResponseEntity<>(new BaseResponse("merchant created successfully", false, userId), HttpStatus.CREATED);
    }

    @PutMapping(path = "/forgot-password/{username}")
    public ResponseEntity<BaseResponse> resetPassword(@PathVariable(name = "username") String username) {
        log.info("resetting password flow initiated for user with username: {}", username);
        userService.resetPassword(username);
        return new ResponseEntity<>(new BaseResponse("password reset link sent to email", false, null), HttpStatus.OK);
    }

    @PutMapping(path = "/update-password")
    @PreAuthorize("hasRole('update_password')")
    public ResponseEntity<BaseResponse> updatePassword(Principal principal, @RequestBody @Valid UpdatePasswordRequest request) {
        log.info("updating password flow initiated for user with username: {}", principal.getName());
        userService.updatePassword(principal.getName(), request);
        return ResponseEntity.ok(new BaseResponse("password update link sent to email", false, null));
    }
}
