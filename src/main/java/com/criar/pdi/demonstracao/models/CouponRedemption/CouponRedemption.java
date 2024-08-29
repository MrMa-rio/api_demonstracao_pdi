package com.criar.pdi.demonstracao.models.CouponRedemption;

import com.criar.pdi.demonstracao.DTOs.CouponRedemptionDTO.CouponRedemptionCommonDTO;
import com.criar.pdi.demonstracao.DTOs.CouponRedemptionDTO.CouponRedemptionDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "coupons_redemption_tbl")
@NoArgsConstructor
@Getter
public class CouponRedemption {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ID;
    private String couponId;
    private String userId;
    private String productId;
    private LocalDate appliedDate;
    private String orderId;
    private LocalDateTime inclusionDate;
    private LocalDateTime updatedDate;
    private LocalDateTime exclusionDate;

    public CouponRedemption(CouponRedemptionDTO couponRedemptionDTO) {

        this.ID = couponRedemptionDTO.ID();
        this.couponId = couponRedemptionDTO.couponId();
        this.userId = couponRedemptionDTO.userId();
        this.productId = couponRedemptionDTO.productId();
        this.appliedDate = couponRedemptionDTO.appliedDate();
        this.orderId = couponRedemptionDTO.orderId();
    }

    public CouponRedemptionCommonDTO getCommonDTO() {
        return new CouponRedemptionCommonDTO(
                this.ID,
                this.couponId,
                this.userId,
                this.productId,
                this.appliedDate,
                this.orderId,
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
