package com.savespendr.backend.merchantservice.data.entity;

import com.savespendr.backend.merchantservice.data.enums.MerchantCategory;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "merchant")
public class Merchant extends BaseEntity {

    @Column(name = "user_id")
    private UUID userId;

    private String name;

    @Column(name = "discount")
    private BigDecimal advertisedDiscount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "merchant_type")
    private MerchantCategory category;

    @Column(name = "image_url")
    private String image;

    @Override
    public int hashCode() {
        return Objects.hash(
                userId,
                name,
                advertisedDiscount,
                category,
                image
        );
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Merchant other = (Merchant) o;
        return Objects.equals(userId, other.userId) &&
                Objects.equals(name, other.name) &&
                Objects.equals(category, other.category) &&
                Objects.equals(image, other.image) &&
                Objects.equals(advertisedDiscount, other.advertisedDiscount);
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAdvertisedDiscount() {
        return advertisedDiscount;
    }

    public void setAdvertisedDiscount(BigDecimal advertisedDiscount) {
        this.advertisedDiscount = advertisedDiscount;
    }

    public MerchantCategory getCategory() {
        return category;
    }

    public void setCategory(MerchantCategory category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
