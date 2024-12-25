package com.savespendr.backend.currencyservice.repository;

import com.savespendr.backend.currencyservice.entity.Currency;
import com.savespendr.backend.currencyservice.enums.Symbol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {
    Optional<Currency> findBySymbol(Symbol symbol);
}
