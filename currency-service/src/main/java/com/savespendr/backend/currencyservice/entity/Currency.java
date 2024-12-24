package com.savespendr.backend.currencyservice.entity;

import com.savespendr.backend.currencyservice.enums.Symbol;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "currency")
public class Currency extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private Symbol symbol;

    private Boolean enabled;

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

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean isSupported() {
        return supported;
    }

    public void setSupported(Boolean supported) {
        this.supported = supported;
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                name,
                symbol,
                enabled,
                supported
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Currency currency = (Currency) o;
        return Objects.equals(name, currency.name) &&
                Objects.equals(symbol, currency.symbol) &&
                Objects.equals(enabled, currency.enabled) &&
                Objects.equals(supported, currency.supported);
    }
}
