package com.savespendr.backend.walletservice.service;

import java.util.UUID;

public interface WalletService {

    void createUserWallets(UUID userId);

}
