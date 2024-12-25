package com.savespendr.backend.currencyservice.dto.request;

import com.savespendr.backend.currencyservice.enums.Symbol;
import jakarta.validation.constraints.NotNull;

public class CreateCurrencyRequest {

    @NotNull(message = "name cannot be null")
    private String name;

    @NotNull(message = "symbol cannot be null")
    private Symbol symbol;

    @NotNull(message = "enabled cannot be null")
    private Boolean enabled;

    @NotNull(message = "supported cannot be null")
    private Boolean supported;

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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getSupported() {
        return supported;
    }

    public void setSupported(Boolean supported) {
        this.supported = supported;
    }
}
