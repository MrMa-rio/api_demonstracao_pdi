package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.components.CouponUtilities.CouponUtilities;
import org.springframework.stereotype.Service;

@Service
public class CouponManagementService {

    private final CouponService couponService;
    private final CouponRedemptionService couponRedemptionService;
    private final CouponUtilities couponUtilities;

    public CouponManagementService(CouponService couponService, CouponRedemptionService couponRedemptionService, CouponUtilities couponUtilities) {
        this.couponService = couponService;
        this.couponRedemptionService = couponRedemptionService;
        this.couponUtilities = couponUtilities;
    }
}
