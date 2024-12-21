package com.savespendr.backend.merchantservice.controller;

import com.savespendr.backend.merchantservice.data.dto.BaseResponse;
import com.savespendr.backend.merchantservice.data.dto.request.MerchantSignupRequest;
import com.savespendr.backend.merchantservice.data.entity.Merchant;
import com.savespendr.backend.merchantservice.service.MerchantService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(path = "/merchants")
public class MerchantController {

    private static final Logger log = LoggerFactory.getLogger(MerchantController.class);

    private final MerchantService merchantService;

    public MerchantController(MerchantService merchantService) {
        this.merchantService = merchantService;
    }

    @PreAuthorize("hasRole('create_merchant')")
    @PostMapping(path = "/register", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Void>> registerMerchant(@RequestBody @Valid MerchantSignupRequest request) {
        log.info("registering merchant with email: {}",request.getEmail());
        merchantService.registerMerchant(request);
        return new ResponseEntity<>(new BaseResponse<>("merchant created", false, null), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('view_merchant')")
    @GetMapping(path = "/{merchant-id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Merchant>> getMerchantDetails(@PathVariable String merchantId) {
        log.info("getting merchant details with id: {}", merchantId);
        Merchant merchant = merchantService.findMerchantById(UUID.fromString(merchantId));
        return new ResponseEntity<>(new BaseResponse<>("successful", false, merchant), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('view_merchant')")
    @GetMapping(path = "/by-user/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<Merchant>> getMerchantDetailsByUserId(@PathVariable String userId) {
        log.info("getting merchant details with id: {}", userId);
        Merchant merchant = merchantService.findByUserId(userId);
        return new ResponseEntity<>(new BaseResponse<>("successful", false, merchant), HttpStatus.OK);
    }

    /*
    * not really needed
    * */
    @PreAuthorize("hasRole('view_merchant_discount')")
    @GetMapping(path = "/{merchant-id}/discount", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BaseResponse<BigDecimal>> getMerchantDiscountedRate(@PathVariable String merchantId) {
        log.info("getting merchant discount rate with id: {}", merchantId);
        BigDecimal discount = merchantService.getMerchantDiscountRate(UUID.fromString(merchantId));
        return new ResponseEntity<>(new BaseResponse<>("successful", false, discount), HttpStatus.OK);
    }
}
