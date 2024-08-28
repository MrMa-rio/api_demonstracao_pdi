package com.criar.pdi.demonstracao.DTOs.Coupon;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import com.criar.pdi.demonstracao.models.Coupon.CouponCreateType;
import com.criar.pdi.demonstracao.models.Coupon.CouponDiscountType;
import com.criar.pdi.demonstracao.models.Coupon.CouponType;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CouponDTO(
        Integer ID,
        @NotNull
        String couponCode,
        @NotNull
        CouponType couponType,
        @NotNull
        String description,
        Double discountValue,
        @NotNull
        CouponDiscountType discountType,
        @NotNull
        LocalDate expirationDate,
        @NotNull
        LocalDate eventStartDate,
        @NotNull
        LocalDate eventEndDate,
        @NotNull
        Integer categoryID,
        @NotNull
        Integer storeID,
        Integer userID,
        @NotNull
        CouponCreateType createdBy,
        @NotNull
        LocalDateTime createdAt
) implements IGenericDTO {
}
