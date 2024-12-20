package com.savespendr.backend.merchantservice.service.impl;

import com.savespendr.backend.merchantservice.data.dto.request.MerchantSignupRequest;
import com.savespendr.backend.merchantservice.data.dto.request.UserSignupRequest;
import com.savespendr.backend.merchantservice.data.entity.Merchant;
import com.savespendr.backend.merchantservice.data.enums.MerchantCategory;
import com.savespendr.backend.merchantservice.exception.NotFoundException;
import com.savespendr.backend.merchantservice.repository.MerchantRepository;
import com.savespendr.backend.merchantservice.service.MerchantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Locale;
import java.util.UUID;

@Service
public class MerchantServiceImpl implements MerchantService {

    private static final Logger log = LoggerFactory.getLogger(MerchantServiceImpl.class);

    private final MerchantRepository merchantRepository;

    @Autowired
    public MerchantServiceImpl(MerchantRepository merchantRepository) {
        this.merchantRepository = merchantRepository;
    }

    @Override
    public void registerMerchant(MerchantSignupRequest request) {
        try {

            UserSignupRequest signupRequest = createSignupRequest(request);
            String merchantUserId = "6589a031-d78a-407b-92fe-9b38b444daab"; // get from auth service
            Merchant merchant = createMerchantInstance(request, merchantUserId);
            merchantRepository.save(merchant);

        } catch (Exception exception) {
            log.error("an error occurred: {}", exception.getMessage());
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    private UserSignupRequest createSignupRequest(MerchantSignupRequest request) {
        UserSignupRequest signupRequest = new UserSignupRequest();
        signupRequest.setFirstName(request.getFirstName());
        signupRequest.setLastName(request.getLastName());
        signupRequest.setPassword("dummy-password");
        signupRequest.setUsername(request.getUsername());
        signupRequest.setEmail(request.getEmail());
        return signupRequest;
    }

    private Merchant createMerchantInstance(MerchantSignupRequest request, String userId) {
        Merchant merchant = new Merchant();
        merchant.setUserId(UUID.fromString(userId));
        merchant.setName(request.getMerchantName().toLowerCase(Locale.ROOT).trim());
        merchant.setAdvertisedDiscount(request.getAdvertisedDiscountRate() != null ? request.getAdvertisedDiscountRate() : BigDecimal.ZERO);
        merchant.setCategory(request.getCategory());
        merchant.setImage(request.getImage());
        return merchant;
    }

    @Override
    public Merchant findMerchantById(UUID merchantId) {
        return merchantRepository.findById(merchantId)
                .orElseThrow(() -> new NotFoundException(String.format("merchant with id %s not found", merchantId)));
    }

    @Override
    public BigDecimal getMerchantDiscountRate(UUID merchantId) {
        return merchantRepository.findById(merchantId)
                .map(Merchant::getAdvertisedDiscount)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public Merchant findByUserId(String userId) {
        return merchantRepository.findByUserId(UUID.fromString(userId))
                .orElseThrow(() -> new NotFoundException(String.format("merchant with user id %s not found", userId)));
    }

    @Override
    public Page<Merchant> getRegisteredMerchants(MerchantCategory category, Pageable pageable) {
        return null;
    }

    @Override
    public Page<Merchant> searchMerchants(String keyword, MerchantCategory category, Pageable pageable) {
        return null;
    }
}
