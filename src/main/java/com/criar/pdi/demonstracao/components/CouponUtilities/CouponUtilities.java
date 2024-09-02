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

    public boolean validationType(ICouponGenericType couponGenericType, CouponCommonDTO couponCommonDTO) {
        if (couponGenericType.getClass() == CouponCreateType.class) {
            if (couponGenericType == CouponCreateType.ADMIN) {
                return true; /*
                TODO: - Primeiro:
                    Crie a logica desta condição;
                TODO: - Segundo:
                    Estruture para se adequar ao design pattern da aplicação;
                */

            }
            if (couponGenericType == CouponCreateType.CLIENT) {
                return true; /*
                TODO: - Primeiro:
                    Crie a logica desta condição;
                TODO: - Segundo:
                    Estruture para se adequar ao design pattern da aplicação;
                */

            }
            if (couponGenericType == CouponCreateType.OWNER) {
                return true; /*
                TODO: - Primeiro:
                    Crie a logica desta condição;
                TODO: - Segundo:
                    Estruture para se adequar ao design pattern da aplicação;
                */

            }
        }
        if (couponGenericType.getClass() == CouponDiscountType.class) {

            if (couponGenericType == CouponDiscountType.PERCENTAGE) {
                return true; /*
                TODO: - Primeiro:
                    Crie a logica desta condição;
                TODO: - Segundo:
                    Estruture para se adequar ao design pattern da aplicação;
                */

            }
            if (couponGenericType == CouponDiscountType.FIXED) {
                return true; /*
                TODO: - Primeiro:
                    Crie a logica desta condição;
                TODO: - Segundo:
                    Estruture para se adequar ao design pattern da aplicação;
                */

            }

        }
        if (couponGenericType.getClass() == CouponType.class) {

            if (couponGenericType == CouponType.CATEGORY) {
                return true; /*
                TODO: - Primeiro:
                    Crie a logica desta condição;
                TODO: - Segundo:
                    Estruture para se adequar ao design pattern da aplicação;
                */

            }
            if (couponGenericType == CouponType.EVENT) {
                return true; /*
                TODO: - Primeiro:
                    Crie a logica desta condição;
                TODO: - Segundo:
                    Estruture para se adequar ao design pattern da aplicação;
                */

            }
            if (couponGenericType == CouponType.STORE) {
                return true; /*
                TODO: - Primeiro:
                    Crie a logica desta condição;
                TODO: - Segundo:
                    Estruture para se adequar ao design pattern da aplicação;
                */

            }
            if (couponGenericType == CouponType.CLIENT) {
                return true; /*
                TODO: - Primeiro:
                    Crie a logica desta condição;
                TODO: - Segundo:
                    Estruture para se adequar ao design pattern da aplicação;
                */

            }
        }
        return false;
    }

    public boolean validationUsageCoupon() {
        return false;
    }


}
