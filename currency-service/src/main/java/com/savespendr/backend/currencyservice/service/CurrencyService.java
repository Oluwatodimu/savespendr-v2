package com.savespendr.backend.currencyservice.service;

import com.savespendr.backend.currencyservice.dto.request.CreateCurrencyRequest;
import com.savespendr.backend.currencyservice.entity.Currency;
import com.savespendr.backend.currencyservice.enums.Symbol;

import java.util.List;

public interface CurrencyService {

    void createCurrency(CreateCurrencyRequest request);

    List<Currency> getSupportedCurrencies();

    void disableCurrency(Symbol symbol);

    Currency findBySymbol(Symbol symbol);
}
