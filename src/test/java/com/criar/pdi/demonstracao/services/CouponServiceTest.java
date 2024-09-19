package com.criar.pdi.demonstracao.services;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Coupon.CouponDTO;
import com.criar.pdi.demonstracao.models.Coupon.Coupon;
import com.criar.pdi.demonstracao.repositories.ICouponRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
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
    @DisplayName("Retorno do cupom atraves da pesquisa por ID via Service")
    void shouldValidateReturnCouponByID() {
        // Arrange
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


        when(iCouponRepository.findById(anyInt())).thenReturn(Optional.of(coupon));
        // Act
        var result = couponService.getCouponByID("1");

        // Assert
        assertNotNull(result);
        assertEquals(coupon.getCommonDTO().couponCode(), result.couponCode());

    }

    @Test
    @DisplayName("Retorno do cupom atraves da pesquisa por Codigo do Cupom via Service")
    void shouldValidateReturnCouponByCouponCode() {
        // Arrange
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
        when(iCouponRepository.findByCouponCode(anyString())).thenReturn(Optional.of(coupon));
        // Act
        var result = couponService.getCouponByCouponCode(anyString());

        // Assert
        assertNotNull(result);
        assertEquals(coupon.getCommonDTO().couponCode(), result.couponCode());
    }

    @Test
    @DisplayName("Cria cupom via Service")
    void shouldValidateCreateCoupon() {
//
//        Coupon coupon = new Coupon(
//                new CouponDTO(
//                        null,
//                        null,
//                        null,
//                        "",
//                        null,
//                        1,
//                        LocalDate.parse("2020-10-10"),
//                        null,
//                        null,
//                        null,
//                        null,
//                        null,
//                        null,
//                        LocalDateTime.now()
//
//                )
//        );
        // Arrange
        CouponDTO couponDTO = new CouponDTO(
                null,
                "TESTECUPOM",
                1,
                null,
                10.00,
                1,
                null,
                null,
                null,
                1,
                1,
                1,
                1,
                null
        );
        Coupon coupon = new Coupon(couponDTO);
        when(iCouponRepository.save(any(Coupon.class))).thenReturn(coupon);
        // Act
        var result = couponService.setCoupon(couponDTO);
        // Assert
        assertNotNull(result);
        assertEquals(coupon.getCommonDTO().couponCode(), result.couponCode());

    }
}
