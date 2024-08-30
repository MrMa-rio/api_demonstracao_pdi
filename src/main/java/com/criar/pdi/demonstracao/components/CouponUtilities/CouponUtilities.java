package com.criar.pdi.demonstracao.components.CouponUtilities;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponCommonDTO;
import com.criar.pdi.demonstracao.models.Coupon.CouponCreateType;
import com.criar.pdi.demonstracao.models.Coupon.CouponDiscountType;
import com.criar.pdi.demonstracao.models.Coupon.CouponType;
import com.criar.pdi.demonstracao.models.Coupon.ICouponGenericType;

import java.time.LocalDate;

public class CouponUtilities {
    public boolean isActive(CouponCommonDTO couponDTO) {
        return couponDTO.exclusionDate() == null;
    }

    public boolean isExpirate(CouponCommonDTO couponDTO) {
        return LocalDate.now().isAfter(couponDTO.expirationDate());
    }

    public boolean validationType(ICouponGenericType couponGenericType, CouponCommonDTO couponCommonDTO){
        if(couponGenericType.getClass() == CouponCreateType.class){

        }
        if(couponGenericType.getClass() == CouponDiscountType.class){

        }
        if (couponGenericType.getClass() == CouponType.class){

        }
        return false;
    }

    public boolean validationUsageCoupon(){
        return false;
    }


}
