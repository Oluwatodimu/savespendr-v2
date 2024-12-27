package com.savespendr.backend.walletservice.dto;

import com.savespendr.backend.walletservice.entity.enums.TransactionPurpose;
import com.savespendr.backend.walletservice.entity.enums.TransactionType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.UUID;

public class DepositRequest {

    @NotNull(message = "user id cannot be null")
    private UUID userId;

    @Positive(message = "amount cannot be less than 1")
    private BigDecimal amount;

    @Positive(message = "type cannot be null")
    private TransactionType type;


    @Positive(message = "purpose cannot be null")
    private TransactionPurpose purpose;


    @NotNull(message = "currency id cannot be null")
    private UUID currencyId;

    @NotNull(message = "reference cannot be null")
    private String reference;

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionPurpose getPurpose() {
        return purpose;
    }

    public void setPurpose(TransactionPurpose purpose) {
        this.purpose = purpose;
    }

    public UUID getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(UUID currencyId) {
        this.currencyId = currencyId;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
