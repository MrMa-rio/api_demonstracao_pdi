package com.criar.pdi.demonstracao.exceptions.CouponRedemption.CouponRedemptionNotFoundException;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponRedemptionNotFoundException extends RuntimeException{
    public CouponRedemptionNotFoundException(String message){
        super(message);
    }
    public CouponRedemptionNotFoundException(){
        super("CUPOM NAO ENCONTRADO");
    }
}
