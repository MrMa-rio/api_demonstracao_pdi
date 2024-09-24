package com.criar.pdi.demonstracao.components;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponCommonDTO;
import com.criar.pdi.demonstracao.components.CouponUtilities.CouponUtilities;
import com.criar.pdi.demonstracao.models.Coupon.Coupon;
import com.criar.pdi.demonstracao.repositories.ICouponRedemptionRepository;
import com.criar.pdi.demonstracao.repositories.ICouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CouponUtilitiesTest {

    @InjectMocks
    CouponUtilities couponUtilities;

    @Mock
    ICouponRepository iCouponRepository;
    @Mock
    ICouponRedemptionRepository iCouponRedemptionRepository;

    @Mock
    CouponCommonDTO couponCommonDTO;

    @Mock
    Coupon couponMock;



    @Test
    @DisplayName("Valida se o metodo retorna um cupom Ativo")
    void shouldValidateCouponActive(){
        //ARRANGE
        when(iCouponRepository.getReferenceById(anyInt())).thenReturn(couponMock);
        when(couponMock.isInactive()).thenReturn(false);
        //ACT
        var result = couponUtilities.isActive(anyInt());
        //ASSERT
        assertTrue(result);
    }

    @Test
    @DisplayName("Valida se o metodo retorna um cupom Inativo")
    void shouldValidateCouponInactive(){
        //ARRANGE
        when(iCouponRepository.getReferenceById(anyInt())).thenReturn(couponMock);
        when(couponMock.isInactive()).thenReturn(true);
        //ACT
        var result = couponUtilities.isActive(anyInt());
        //ASSERT
        assertFalse(result);
    }

    
}
