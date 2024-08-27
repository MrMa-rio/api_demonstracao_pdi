package com.criar.pdi.demonstracao.exceptions.Coupon.CouponIdentifyException;

public class CouponIdentifyException extends RuntimeException{
    public CouponIdentifyException(String message){
        super(message);
    }
    public CouponIdentifyException(){
        super("IDENTIFICADOR DO CUPOM INVALIDO");
    }
}
