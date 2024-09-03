package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Coupon.CouponOfUserDTO;
import com.criar.pdi.demonstracao.DTOs.CouponRedemptionDTO.CouponRedemptionCommonDTO;
import com.criar.pdi.demonstracao.DTOs.CouponRedemptionDTO.CouponRedemptionDTO;
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

    public CouponRedemptionCommonDTO setCouponRedemption(CouponRedemptionDTO couponRedemptionDTO) {
        CouponCommonDTO couponDTO = couponService.getCouponByID(couponRedemptionDTO.couponId());

        if (!couponUtilities.isActive(
                Integer.valueOf(
                        couponRedemptionDTO.couponId()
                ))) {
            throw new RuntimeException("O cupom é inativo - management");
        }

        if (
                !couponUtilities.validationUsageCoupon(
                        new CouponOfUserDTO(
                                couponRedemptionDTO.userId(),
                                couponRedemptionDTO.couponId()
                        )
                )
        ) {
            throw new RuntimeException("O cupom ja e utilizado - management");
        }
        if (couponUtilities.isExpirate(couponDTO.expirationDate())) {
            throw new RuntimeException("O cupom esta expirado - management");
        }
        if (couponUtilities.isExpirateEvent(couponDTO.eventStartDate(), couponDTO.eventEndDate())) {
            throw new RuntimeException("O evento desse cupom foi expirado - management");
        }

//        if(!couponUtilities.validationType(couponDTO.couponType(), couponDTO)){
//            throw new RuntimeException("O evento desse cupom foi expirado - management");
//        }
//        if(!couponUtilities.validationType(couponDTO.discountType(), couponDTO)){
//            throw new RuntimeException("O evento desse cupom foi expirado - management");
//        }
//        if(!couponUtilities.validationType(couponDTO.createdBy(), couponDTO)){
//            throw new RuntimeException("O evento desse cupom foi expirado - management");
//        }

        return couponRedemptionService.setCouponRedemption(couponRedemptionDTO); // continuar as verificacoes, possivelmente colocaremos essa implemenmtancao na cupomUtilies
    }


}
