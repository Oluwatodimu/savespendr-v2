package com.savespendr.backend.currencyservice.controller;

import com.savespendr.backend.currencyservice.dto.BaseResponse;
import com.savespendr.backend.currencyservice.dto.request.CreateCurrencyRequest;
import com.savespendr.backend.currencyservice.entity.Currency;
import com.savespendr.backend.currencyservice.enums.Symbol;
import com.savespendr.backend.currencyservice.service.CurrencyService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private static final Logger log = LoggerFactory.getLogger(CurrencyController.class);

    private final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PostMapping(path = "/")
    public ResponseEntity<BaseResponse<Void>> createCurrency(@RequestBody @Valid CreateCurrencyRequest request) {
        log.info("creating new currency: {}", request.getSymbol());
        currencyService.createCurrency(request);
        return new ResponseEntity<>(new BaseResponse<>("currency created", false, null), HttpStatus.CREATED);
    }

    @GetMapping(path = "/")
    public ResponseEntity<BaseResponse<List<Currency>>> getSupportedCurrencies() {
        log.info("creating supported currencies");
        List<Currency> currencies = currencyService.getSupportedCurrencies();
        return new ResponseEntity<>(new BaseResponse<>("successful", false, currencies), HttpStatus.OK);
    }

    @PutMapping(path = "/{symbol}")
    public ResponseEntity<BaseResponse<Void>> disableCurrency(@PathVariable Symbol symbol) {
        log.info("disabling currency: {}", symbol);
        currencyService.disableCurrency(symbol);
        return new ResponseEntity<>(new BaseResponse<>("successful", false, null), HttpStatus.OK);
    }

    @GetMapping(path = "/{symbol}")
    public ResponseEntity<BaseResponse<Currency>> findBySymbol(@PathVariable Symbol symbol) {
        log.info("disabling currency: {}", symbol);
        Currency currency = currencyService.findBySymbol(symbol);
        return new ResponseEntity<>(new BaseResponse<>("successful", false, currency), HttpStatus.OK);
    }
}
