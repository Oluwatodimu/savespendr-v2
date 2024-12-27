package com.savespendr.backend.walletservice.service.impl;

import com.savespendr.backend.walletservice.dto.DepositRequest;
import com.savespendr.backend.walletservice.entity.Wallet;
import com.savespendr.backend.walletservice.entity.enums.Symbol;
import com.savespendr.backend.walletservice.repository.WalletRepository;
import com.savespendr.backend.walletservice.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        // create wallet based on NGN and USD
        // save
    }

    private Wallet createWallet(UUID userId, Symbol symbol) {

    }
}
