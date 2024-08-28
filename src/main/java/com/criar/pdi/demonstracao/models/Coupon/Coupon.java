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
    private Integer couponType;
    private String description;
    private Double discountValue;
    private Integer discountType;
    private LocalDate expirationDate;
    private LocalDate eventStartDate;
    private LocalDate eventEndDate;
    @Column(name = "category_id")
    private Integer categoryID;
    @Column(name = "store_id")
    private Integer storeID;
    @Column(name = "user_id")
    private Integer userID;
    private Integer createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;

    public Coupon(CouponDTO couponDTO) {
        this.ID = couponDTO.ID();
        this.couponCode = couponDTO.couponCode();
        this.couponType = couponDTO.couponType().code();
        this.description = couponDTO.description();
        this.discountValue = couponDTO.discountValue();
        this.discountType = couponDTO.discountType().code();
        this.expirationDate = couponDTO.expirationDate();
        this.eventStartDate = couponDTO.eventStartDate();
        this.eventEndDate = couponDTO.eventEndDate();
        this.categoryID = couponDTO.categoryID();
        this.storeID = couponDTO.storeID();
        this.userID = couponDTO.userID();
        this.createdBy = couponDTO.createdBy().code();
        this.createdAt = couponDTO.createdAt();
    }

    public CouponCommonDTO getCommonDTO() {
        return new CouponCommonDTO(
                this.ID,
                this.couponCode,
                CouponType.get(this.couponType),
                this.description,
                this.discountValue,
                CouponDiscountType.get(this.discountType),
                this.expirationDate,
                this.eventStartDate,
                this.eventEndDate,
                this.categoryID,
                this.storeID,
                this.userID,
                CouponCreateType.get(this.createdBy),
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
