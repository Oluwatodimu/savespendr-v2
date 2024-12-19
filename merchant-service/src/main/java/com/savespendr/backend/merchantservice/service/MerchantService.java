package com.savespendr.backend.merchantservice.service;

import com.savespendr.backend.merchantservice.data.dto.request.MerchantSignupRequest;
import com.savespendr.backend.merchantservice.data.entity.Merchant;
import com.savespendr.backend.merchantservice.data.enums.MerchantCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.UUID;

public interface MerchantService {

    void registerMerchant(MerchantSignupRequest merchantSignupRequest);

    Merchant findMerchantById(UUID merchantId);

    BigDecimal getMerchantDiscountRate(UUID merchantId);

    Merchant findByUserId(String userId);

    Page<Merchant> getRegisteredMerchants(MerchantCategory category, Pageable pageable);

    Page<Merchant> searchMerchants(String keyword, MerchantCategory category, Pageable pageable);
}
