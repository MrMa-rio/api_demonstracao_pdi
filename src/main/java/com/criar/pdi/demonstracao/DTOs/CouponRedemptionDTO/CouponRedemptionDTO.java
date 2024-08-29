package com.criar.pdi.demonstracao.DTOs.CouponRedemptionDTO;


import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CouponRedemptionDTO(
        String ID,
        @NotNull
        String couponId,
        @NotNull
        String userId,
        @NotNull
        String productId,
        @NotNull
        LocalDate appliedDate,
        @NotNull
        String orderId
) implements IGenericDTO {
}
