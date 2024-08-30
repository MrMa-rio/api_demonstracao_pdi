package com.criar.pdi.demonstracao.components.CouponUtilities;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponCommonDTO;

import java.time.LocalDate;

public class CouponUtilities {
    public boolean isActive(CouponCommonDTO couponDTO) {
        return couponDTO.exclusionDate() == null;
    }

    public boolean isExpirated(CouponCommonDTO couponDTO) {
        return LocalDate.now().isAfter(couponDTO.expirationDate());
    }
}
