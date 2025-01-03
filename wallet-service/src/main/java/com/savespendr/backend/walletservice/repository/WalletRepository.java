package com.savespendr.backend.walletservice.repository;

import com.savespendr.backend.walletservice.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface WalletRepository extends JpaRepository<Wallet, UUID> {
}
