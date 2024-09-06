package com.criar.pdi.demonstracao.components.ProductUtilities;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponCommonDTO;
import com.criar.pdi.demonstracao.models.Coupon.CouponDiscountType;
import org.springframework.stereotype.Component;

@Component
public class ProductUtilities {

    public Double calculatePrice(CouponCommonDTO couponDTO, Double price){

        if(couponDTO.discountType() == CouponDiscountType.FIXED){
            Double newPrice = price - couponDTO.discountValue();
            if(price < 1) return 0.0; // TODO: Logica simples, futuramente colocar condição caso o item for do mesmo valor do cupom, limitação do valor descontado
        }
        return 0.0;
    }
}
