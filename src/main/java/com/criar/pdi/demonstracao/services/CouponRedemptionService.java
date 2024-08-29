package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.CouponRedemptionDTO.CouponRedemptionCommonDTO;
import com.criar.pdi.demonstracao.DTOs.CouponRedemptionDTO.CouponRedemptionDTO;
import com.criar.pdi.demonstracao.exceptions.CouponRedemption.CouponRedemptionDuplicateDataException.CouponRedemptionDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.CouponRedemption.CouponRedemptionGenericException.CouponRedemptionGenericException;
import com.criar.pdi.demonstracao.exceptions.CouponRedemption.CouponRedemptionIdentifyException.CouponRedemptionIdentifyException;
import com.criar.pdi.demonstracao.exceptions.CouponRedemption.CouponRedemptionNotFoundException.CouponRedemptionNotFoundException;
import com.criar.pdi.demonstracao.models.CouponRedemption.CouponRedemption;
import com.criar.pdi.demonstracao.repositories.ICouponRedemptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CouponRedemptionService {

    @Autowired
    private ICouponRedemptionRepository iCouponRedemptionRepository;


    private Page<CouponRedemptionCommonDTO> page(Page<CouponRedemption> productPage, Pageable pageable) {
        List<CouponRedemptionCommonDTO> storeCommonDTOList = productPage.getContent().stream()
                .map(CouponRedemption::getCommonDTO).toList();
        return new PageImpl<>(storeCommonDTOList, pageable, productPage.getTotalElements());
    }

    public CouponRedemptionCommonDTO getCouponRedemptionByID(String couponRedemptionID) {
        try {
            CouponRedemption coupon = iCouponRedemptionRepository.findById(Integer.valueOf(couponRedemptionID)).orElseThrow();
            return coupon.getCommonDTO();
        } catch (NoSuchElementException e) {
            throw new CouponRedemptionNotFoundException();
        } catch (NumberFormatException e) {
            throw new CouponRedemptionIdentifyException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CouponRedemptionCommonDTO setCouponRedemption(CouponRedemptionDTO couponDTO) {
        try {
            CouponRedemption coupon = new CouponRedemption(couponDTO);
            coupon.setInclusionDate();
            iCouponRedemptionRepository.save(coupon);
            return coupon.getCommonDTO();
        } catch (DataIntegrityViolationException e) {
            throw new CouponRedemptionDuplicateDataException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCouponRedemption(String couponID) {
        try {
            CouponRedemption coupon = iCouponRedemptionRepository.findById(Integer.valueOf(couponID)).orElseThrow();
            if (coupon.isInactive()) {
                throw new CouponRedemptionGenericException("ESTE REGISTRO DE CUPOM JA ESTA INATIVADO!!");
            }
            coupon.setExclusionDate();
            iCouponRedemptionRepository.saveAndFlush(coupon);
        } catch (NoSuchElementException e) {
            throw new CouponRedemptionNotFoundException();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }


    public Page<CouponRedemptionCommonDTO> getCouponRedemptionByUserIdAndCouponId(String userId, String couponId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CouponRedemption> couponRedemptionPage = iCouponRedemptionRepository.findAllByUserIdAndCouponId(userId, couponId, pageable);
        return page(couponRedemptionPage, pageable);
    }
}
