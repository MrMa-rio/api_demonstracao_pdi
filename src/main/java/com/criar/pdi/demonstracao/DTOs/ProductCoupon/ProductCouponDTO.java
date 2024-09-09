package com.criar.pdi.demonstracao.DTOs.ProductCoupon;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponCommonDTO;
import com.criar.pdi.demonstracao.DTOs.Generic.IGenericDTO;
import com.criar.pdi.demonstracao.DTOs.ProductInCart.ProductInCartCommonDTO;

public record ProductCouponDTO(
        ProductInCartCommonDTO product,
        CouponCommonDTO coupon
) implements IGenericDTO {
}
