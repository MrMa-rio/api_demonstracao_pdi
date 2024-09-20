package com.criar.pdi.demonstracao.components;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponCommonDTO;
import com.criar.pdi.demonstracao.components.CouponUtilities.CouponUtilities;
import com.criar.pdi.demonstracao.repositories.ICouponRedemptionRepository;
import com.criar.pdi.demonstracao.repositories.ICouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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



    @Test
    @DisplayName("")
    void teste(){
        //ARRANGE



        //ACT

        //ASSERT

    }
}
