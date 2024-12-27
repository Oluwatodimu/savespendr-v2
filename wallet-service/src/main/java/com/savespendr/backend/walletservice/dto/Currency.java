package com.savespendr.backend.walletservice.dto;

import com.savespendr.backend.walletservice.entity.enums.Symbol;

import java.util.UUID;

public class Currency {

    private UUID id;

    private String name;

    private Symbol symbol;

    private Boolean enabled;

    private Boolean supported;

    public Currency() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Symbol getSymbol() {
        return symbol;
    }

    public void setSymbol(Symbol symbol) {
        this.symbol = symbol;
    }

    public Boolean getSupported() {
        return supported;
    }

    public void setSupported(Boolean supported) {
        this.supported = supported;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
