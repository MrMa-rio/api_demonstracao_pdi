package com.criar.pdi.demonstracao.DTOs.Coupon;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import com.criar.pdi.demonstracao.models.Coupon.Coupon;
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
        Integer couponType,
        String description,
        @NotNull
        Double discountValue,
        @NotNull
        Integer discountType,
        LocalDate expirationDate,
        LocalDate eventStartDate,
        LocalDate eventEndDate,
        Integer categoryID,
        Integer storeID,
        Integer userID,
        @NotNull
        Integer createdBy,
        @NotNull
        LocalDateTime createdAt
) implements IGenericDTO {}
