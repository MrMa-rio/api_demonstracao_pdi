package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponCommonDTO;
import com.criar.pdi.demonstracao.models.Coupon.Coupon;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CouponManagementService {

    private final CouponService couponService;
    private final CouponRedemptionService couponRedemptionService;

    public CouponManagementService(CouponService couponService, CouponRedemptionService couponRedemptionService) {
        this.couponService = couponService;
        this.couponRedemptionService = couponRedemptionService;
    }

    public boolean existCouponCode(String couponCode) {
        try {
            CouponCommonDTO couponDTO = couponService.getCouponByCouponCode(couponCode);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public boolean isActive(CouponCommonDTO couponDTO) {
        return couponDTO.exclusionDate() == null;
    }
}
