package com.savespendr.backend.currencyservice.service.impl;

import com.savespendr.backend.currencyservice.dto.request.CreateCurrencyRequest;
import com.savespendr.backend.currencyservice.entity.Currency;
import com.savespendr.backend.currencyservice.enums.Symbol;
import com.savespendr.backend.currencyservice.exception.NotFoundException;
import com.savespendr.backend.currencyservice.repository.CurrencyRepository;
import com.savespendr.backend.currencyservice.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    @Autowired
    public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public void createCurrency(CreateCurrencyRequest request) {
         Optional<Currency> currencyOptional = currencyRepository.findBySymbol(request.getSymbol());
         if (currencyOptional.isPresent()) {
             throw new RuntimeException(String.format("Currency %s already exists", request.getSymbol()));
         }

         Currency currency = new Currency();
         currency.setName(request.getName().toLowerCase(Locale.ROOT).trim());
         currency.setSymbol(request.getSymbol());
         currency.setEnabled(true);
         currency.setSupported(true);
         currencyRepository.save(currency);
    }

    @Override
    public List<Currency> getSupportedCurrencies() {
        return currencyRepository.findAll()
                .stream()
                .filter(Currency::isSupported)
                .filter(Currency::isEnabled)
                .toList();
    }

    @Override
    public void disableCurrency(Symbol symbol) {
       currencyRepository.findBySymbol(symbol)
               .map(currency -> {
                   currency.setEnabled(false);
                   return currency;
               })
               .map(currencyRepository::save)
               .orElseThrow(() -> new NotFoundException("currency not found"));
    }

    @Override
    public Currency findBySymbol(Symbol symbol) {
        return currencyRepository.findBySymbol(symbol)
                .filter(Currency::isEnabled)
                .filter(Currency::isSupported)
                .orElseThrow(() -> new NotFoundException("currency not enabled/supported"));
    }
}
