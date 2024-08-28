package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Coupon.CouponDTO;
import com.criar.pdi.demonstracao.exceptions.Coupon.CouponDuplicateDataException.CouponDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Coupon.CouponGenericException.CouponGenericException;
import com.criar.pdi.demonstracao.exceptions.Coupon.CouponIdentifyException.CouponIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Coupon.CouponNotFoundException.CouponNotFoundException;
import com.criar.pdi.demonstracao.models.Coupon.Coupon;
import com.criar.pdi.demonstracao.repositories.ICouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class CouponService {

    @Autowired
    private ICouponRepository iCouponRepository;

    public CouponCommonDTO getCouponByID(String couponID) {

        try {
            Coupon coupon = iCouponRepository.findById(Integer.valueOf(couponID)).orElseThrow();
            return coupon.getCommonDTO();
        } catch (NoSuchElementException e) {
            throw new CouponNotFoundException();
        } catch (NumberFormatException e) {
            throw new CouponIdentifyException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CouponCommonDTO setCoupon(CouponDTO couponDTO) {
        try {
            Coupon coupon = new Coupon(couponDTO);
            coupon.setInclusionDate();
            iCouponRepository.save(coupon);
            return coupon.getCommonDTO();
        } catch (DataIntegrityViolationException e) {
            throw new CouponDuplicateDataException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCoupon(String couponID) {
        try {
            Coupon coupon = iCouponRepository.findById(Integer.valueOf(couponID)).orElseThrow();
            if (coupon.isInactive()) {
                throw new CouponGenericException("ESTE CUPOM JA ESTA INATIVADO!!");
            }
            coupon.setExclusionDate();
            iCouponRepository.saveAndFlush(coupon);
        } catch (NoSuchElementException e) {
            throw new CouponNotFoundException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
