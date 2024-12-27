package com.savespendr.backend.walletservice.repository;

import com.savespendr.backend.walletservice.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
