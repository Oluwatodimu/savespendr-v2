package com.savespendr.backend.merchantservice.data.dto.request;

import com.savespendr.backend.merchantservice.data.enums.MerchantCategory;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class MerchantSignupRequest {

    @NotNull(message = "first name cannot be null")
    private String firstName;

    @NotNull(message = "last name cannot be null")
    private String lastName;

    @Email(message = "email should be in required format")
    @NotNull(message = "email cannot be null")
    private String email;

    @NotNull(message = "username cannot be null")
    private String username;

    private String phoneNumber;

    @NotNull(message = "merchant name cannot be null")
    private String merchantName;

    private BigDecimal advertisedDiscountRate;

    private MerchantCategory category;

    private String image;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public BigDecimal getAdvertisedDiscountRate() {
        return advertisedDiscountRate;
    }

    public void setAdvertisedDiscountRate(BigDecimal advertisedDiscountRate) {
        this.advertisedDiscountRate = advertisedDiscountRate;
    }

    public MerchantCategory getCategory() {
        return category;
    }

    public void setCategory(MerchantCategory category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
