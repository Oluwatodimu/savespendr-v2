package com.savespendr.backend.currencyservice.service;

import com.savespendr.backend.currencyservice.dto.BaseResponse;
import com.savespendr.backend.currencyservice.dto.request.CreateCurrencyRequest;
import com.savespendr.backend.currencyservice.entity.Currency;
import com.savespendr.backend.currencyservice.enums.Symbol;

import java.util.List;

public interface CurrencyService {

    BaseResponse<Void> createCurrency(CreateCurrencyRequest request);

    BaseResponse<List<Currency>> getSupportedCurrencies();

    BaseResponse<Void> disableCurrency(Symbol symbol);

    BaseResponse<Currency> findBySymbol(Symbol symbol);
}
