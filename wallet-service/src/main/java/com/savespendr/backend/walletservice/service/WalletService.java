package com.savespendr.backend.walletservice.service;

import com.savespendr.backend.walletservice.dto.DepositRequest;

import java.util.UUID;

public interface WalletService {

    void createUserWallets(UUID userId);

}
