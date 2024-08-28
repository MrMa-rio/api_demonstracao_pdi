package com.criar.pdi.demonstracao.DTOs.Coupon;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import com.criar.pdi.demonstracao.models.Coupon.CouponCreateType;
import com.criar.pdi.demonstracao.models.Coupon.CouponDiscountType;
import com.criar.pdi.demonstracao.models.Coupon.CouponType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CouponCommonDTO(
        Integer ID,
        String couponCode,
        CouponType couponType,
        String description,
        Double discountValue,
        CouponDiscountType discountType,
        LocalDate expirationDate,
        LocalDate eventStartDate,
        LocalDate eventEndDate,
        Integer categoryID,
        Integer storeID,
        Integer userID,
        CouponCreateType createdBy,
        LocalDateTime createdAt,
        LocalDateTime inclusionDate,
        LocalDateTime updatedDate,
        LocalDateTime exclusionDate
) implements IGenericDTO {
}
