package com.criar.pdi.demonstracao.components.CouponUtilities;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Coupon.CouponOfUserDTO;
import com.criar.pdi.demonstracao.exceptions.Coupon.CouponGenericException.CouponGenericException;
import com.criar.pdi.demonstracao.models.Coupon.*;
import com.criar.pdi.demonstracao.models.CouponRedemption.CouponRedemption;
import com.criar.pdi.demonstracao.repositories.ICouponRedemptionRepository;
import com.criar.pdi.demonstracao.repositories.ICouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CouponUtilities {

    @Autowired
    private ICouponRepository iCouponRepository;
    @Autowired
    private ICouponRedemptionRepository iCouponRedemptionRepository;

    public boolean isActive(Integer couponID) {
        Coupon coupon = iCouponRepository.getReferenceById(couponID);
        return !coupon.isInactive();
    }

    public boolean isExpirate(LocalDate expirationDate) {
        if (expirationDate == null) return false;
        return LocalDate.now().isAfter(expirationDate);
    }

    public boolean isExpirateEvent(LocalDate eventStartDate, LocalDate eventEndDate) {
        if (eventStartDate == null || eventEndDate == null) return false;
        return !(LocalDate.now().isAfter(eventStartDate) && LocalDate.now().isBefore(eventEndDate));
    }

    public boolean validationType(ICouponGenericType couponGenericType, CouponCommonDTO couponCommonDTO) {
        if (couponGenericType.getClass() == CouponCreateType.class) {
            Optional<Coupon> coupon = iCouponRepository.findByCouponCode(couponCommonDTO.couponCode());
            if (couponGenericType == CouponCreateType.ADMIN) {
                if (coupon.isPresent()) {
                    CouponCommonDTO couponDTO = coupon.get().getCommonDTO();
                    if (couponDTO.userID() != null && !couponDTO.userID().equals(couponCommonDTO.userID())) {
                        return false;
                    }
                    if (couponDTO.categoryID() != null && !couponDTO.categoryID().equals(couponCommonDTO.categoryID())) {
                        return false;
                    }
                }
                /*
                TODO: - Segundo:
                    Estruture para se adequar ao design pattern da aplicação;
                */

            }
            if (couponGenericType == CouponCreateType.CLIENT) {
                if (coupon.isPresent()) {
                    CouponCommonDTO couponDTO = coupon.get().getCommonDTO();
                    if (couponDTO.userID() != null && !couponDTO.userID().equals(couponCommonDTO.userID())) {
                        return false;
                    }
                    if (couponDTO.categoryID() != null && !couponDTO.categoryID().equals(couponCommonDTO.categoryID())) {
                        return false;
                    }
                }
                /*
                TODO: - Segundo:
                    Estruture para se adequar ao design pattern da aplicação;
                */

            }
            if (couponGenericType == CouponCreateType.OWNER) {
                if (coupon.isPresent()) {
                    CouponCommonDTO couponDTO = coupon.get().getCommonDTO();
                    if (couponDTO.userID() != null && !couponDTO.userID().equals(couponCommonDTO.userID())) {
                        return false;
                    }
                    if (couponDTO.categoryID() != null && !couponDTO.categoryID().equals(couponCommonDTO.categoryID())) {
                        return false;
                    }
                }
                 /*
                TODO: - Primeiro:
                    Crie a logica desta condição;
                TODO: - Segundo:
                    Estruture para se adequar ao design pattern da aplicação;
                */

            }
        }

        return true;
    }

    public boolean validationUsageCoupon(CouponOfUserDTO couponOfUserDTO) {
        List<CouponRedemption> couponPage = iCouponRedemptionRepository.findAllByUserIdAndCouponId(
                couponOfUserDTO.userID(),
                couponOfUserDTO.couponID()
        );
        ArrayList<CouponRedemption> couponList = new ArrayList<>();
        couponPage.forEach(couponRedemption -> {
            if (!couponRedemption.isInactive()) {
                couponList.add(couponRedemption);
            }
        });
        return couponList.isEmpty();
    }

}
