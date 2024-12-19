package com.savespendr.backend.merchantservice.repository;

import com.savespendr.backend.merchantservice.data.entity.Merchant;
import com.savespendr.backend.merchantservice.data.enums.MerchantCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface MerchantRepository extends JpaRepository<Merchant, UUID> {

    Optional<Merchant> findByUserId(UUID uuid);

    Page<Merchant> findAllByCategory(MerchantCategory category, Pageable pageable);

    Page<Merchant> findAllByNameContaining(String keyword, Pageable pageable);

    Page<Merchant> findAllByNameContainingAndCategory(String keyword, MerchantCategory category, Pageable pageable);
}
