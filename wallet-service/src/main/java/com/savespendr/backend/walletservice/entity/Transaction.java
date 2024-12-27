package com.savespendr.backend.walletservice.entity;

import com.savespendr.backend.walletservice.entity.enums.TransactionPurpose;
import com.savespendr.backend.walletservice.entity.enums.TransactionStatus;
import com.savespendr.backend.walletservice.entity.enums.TransactionType;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "wallet_transaction")
public class Transaction extends BaseEntity {

    @Column(name = "user_id")
    private UUID userId;

    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Enumerated(EnumType.STRING)
    private TransactionPurpose purpose;

    @Column(name = "currency_id")
    private UUID currencyId;

    private String reference;

    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    private Boolean paid;

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

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
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

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
}
