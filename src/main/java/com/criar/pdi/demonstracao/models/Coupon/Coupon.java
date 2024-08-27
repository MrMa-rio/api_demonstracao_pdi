package com.criar.pdi.demonstracao.models.Coupon;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Coupon.CouponDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "coupons_tbl")
@NoArgsConstructor
@Getter
public class Coupon {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String couponCode;
    private CouponType couponType;
    private String description;
    private Double discountValue;
    private CouponDiscountType discountType;
    private LocalDate expirationDate;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    @Column(name = "category_id")
    private Integer categoryID;
    @Column(name = "store_id")
    private Integer storeID;
    @Column(name = "user_id")
    private Integer userID;
    private LocalDateTime createdBy;
    private CouponCreateType createdAt;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;

    public Coupon(CouponDTO couponDTO) {
        this.ID = couponDTO.ID();
        this.couponCode = couponDTO.couponCode();
        this.couponType = couponDTO.couponType();
        this.description = couponDTO.description();
        this.discountValue = couponDTO.discountValue();
        this.discountType = couponDTO.discountType();
        this.expirationDate = couponDTO.expirationDate();
        this.eventStartDate = couponDTO.eventStartDate();
        this.eventEndDate = couponDTO.eventEndDate();
        this.categoryID = couponDTO.categoryID();
        this.storeID = couponDTO.storeID();
        this.userID = couponDTO.userID();
        this.createdBy = couponDTO.createdBy();
        this.createdAt = couponDTO.createdAt();
    }

    public CouponCommonDTO getCommonDTO() {
        return new CouponCommonDTO(
                this.ID,
                this.couponCode,
                this.couponType,
                this.description,
                this.discountValue,
                this.discountType,
                this.expirationDate,
                this.eventStartDate,
                this.eventEndDate,
                this.categoryID,
                this.storeID,
                this.userID,
                this.createdBy,
                this.createdAt,
                this.inclusionDate,
                this.updatedDate,
                this.exclusionDate
        );
    }

    public void setInclusionDate() {
        this.inclusionDate = LocalDateTime.now();
    }

    private void setUpdateDate() {
        this.updatedDate = LocalDateTime.now();
    }

    public void setExclusionDate() {
        this.exclusionDate = LocalDateTime.now();
    }

    public boolean isInactive() {
        return this.exclusionDate != null;
    }


}
