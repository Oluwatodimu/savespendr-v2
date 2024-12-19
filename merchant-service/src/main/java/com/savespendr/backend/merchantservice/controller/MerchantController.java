package com.savespendr.backend.merchantservice.controller;

import com.savespendr.backend.merchantservice.data.dto.BaseResponse;
import com.savespendr.backend.merchantservice.data.dto.request.MerchantSignupRequest;
import com.savespendr.backend.merchantservice.service.MerchantService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = "/api/v2/merchants")
public class MerchantController {

    private static final Logger log = LoggerFactory.getLogger(MerchantController.class);

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PreAuthorize("hasRole('create_merchant')")
    @PostMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse> registerMerchant(@RequestBody @Valid MerchantSignupRequest request) {
        log.info("registering merchant with email: {}",request.getEmail());
        merchantService.registerMerchant(request);
        return new ResponseEntity<>(new BaseResponse("merchant created", false, null), HttpStatus.CREATED);
    }
}
