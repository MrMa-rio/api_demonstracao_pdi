package com.criar.pdi.demonstracao.components.ProductUtilities;

import com.criar.pdi.demonstracao.DTOs.Coupon.CouponCommonDTO;
import com.criar.pdi.demonstracao.models.Coupon.CouponDiscountType;
import org.springframework.stereotype.Component;

@Component
public class ProductUtilities {

    public Double calculatePrice(CouponDiscountType discountType, Double price, Double discountValue){

        if(discountType == CouponDiscountType.FIXED){
            Double newPrice = price - discountValue;
            if(price > 0) return newPrice; // TODO: Logica simples, futuramente colocar condição caso o item for do mesmo valor do cupom, limitação do valor descontado
        }
        if(discountType == CouponDiscountType.PERCENTAGE){
            Double discountPrice = price / discountValue;
            Double newPrice = price - discountPrice;
            if(price > 0) return newPrice;
        }
        return price;
    }
}
