package com.savespendr.backend.walletservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "wallet_asset")
public class Wallet extends BaseEntity {

    @Column(name = "available_balance")
    private BigDecimal availableBalance;

    @Column(name = "reserved_balance")
    private BigDecimal reservedBalance;

    private Boolean locked;

    @Column(name = "currency_id")
    private UUID currencyId;

    @Column(name = "user_id")
    private UUID userId;

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public BigDecimal getReservedBalance() {
        return reservedBalance;
    }

    public void setReservedBalance(BigDecimal reservedBalance) {
        this.reservedBalance = reservedBalance;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public UUID getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(UUID currencyId) {
        this.currencyId = currencyId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
