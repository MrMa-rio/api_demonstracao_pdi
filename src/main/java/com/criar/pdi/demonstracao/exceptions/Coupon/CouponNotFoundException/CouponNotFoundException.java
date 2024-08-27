package com.criar.pdi.demonstracao.exceptions.Coupon.CouponNotFoundException;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponNotFoundException extends RuntimeException{
    public CouponNotFoundException(String message){
        super(message);
    }
    public CouponNotFoundException(){
        super("CUPOM NAO ENCONTRADO");
    }
}
