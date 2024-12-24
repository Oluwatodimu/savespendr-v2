package com.savespendr.backend.currencyservice.repository;

import com.savespendr.backend.currencyservice.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CurrencyRepository extends JpaRepository<Currency, UUID> {

}
