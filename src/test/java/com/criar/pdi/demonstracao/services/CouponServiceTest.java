package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Coupon.CouponDTO;
import com.criar.pdi.demonstracao.models.Coupon.Coupon;
import com.criar.pdi.demonstracao.repositories.ICouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CouponServiceTest {

    @InjectMocks
    private CouponService couponService;
    @Mock
    private ICouponRepository iCouponRepository;
    @Mock
    private Coupon couponMock;
    @Mock
    private CouponCommonDTO couponCommonDTO;

    @Test
    @DisplayName("Teste")
    void shouldReturnCoupon(){

        Coupon coupon = new Coupon(
                new CouponDTO(
                        1,
                        "1COUPOMTESTE",
                        1,
                        "",
                        10.00,
                        1,
                        LocalDate.parse("2020-10-10"),
                        null,
                        null,
                        null,
                        null,
                        null,
                        1,
                        LocalDateTime.now()

                )
        );

        // Arrange
        when(iCouponRepository.findById(anyInt())).thenReturn(Optional.of(coupon));

        // Act
        CouponCommonDTO result = couponService.getCouponByID("3");

        // Assert
        assertNotNull(result);
        //verify(iCouponRepository).findById(2);

    }
}
