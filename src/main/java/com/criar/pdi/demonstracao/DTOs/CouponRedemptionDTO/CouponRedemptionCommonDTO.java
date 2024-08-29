package com.criar.pdi.demonstracao.DTOs.CouponRedemptionDTO;

import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CouponRedemptionCommonDTO(
        String ID,
        String couponId,
        String userId,
        String productId,
        LocalDate appliedDate,
        String orderId,
        LocalDateTime inclusionDate,
        LocalDateTime updatedDate,
        LocalDateTime exclusionDate) implements IGenericDTO {
}
