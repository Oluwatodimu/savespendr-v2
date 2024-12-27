package com.savespendr.backend.walletservice.service.impl;

import com.savespendr.backend.walletservice.dto.Currency;
import com.savespendr.backend.walletservice.entity.Wallet;
import com.savespendr.backend.walletservice.repository.WalletRepository;
import com.savespendr.backend.walletservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public void createUserWallets(UUID userId) {
        // get the available currencies
        List<Currency> currenciesSupported = new ArrayList<>();
        currenciesSupported.stream()
                .map(currency -> createWallet(userId, currency.getId()))
                .filter(Objects::nonNull)
                .forEach(walletRepository::save);
    }

    private Wallet createWallet(UUID userId, UUID currencyId) {
        Wallet wallet = new Wallet();
        wallet.setAvailableBalance(BigDecimal.ZERO);
        wallet.setReservedBalance(BigDecimal.ZERO);
        wallet.setLocked(false);
        wallet.setCurrencyId(currencyId);
        wallet.setUserId(userId);
        return wallet;
    }
}
