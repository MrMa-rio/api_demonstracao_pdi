package com.criar.pdi.demonstracao.exceptions.CouponRedemption.CouponRedemptionIdentifyException;

public class CouponRedemptionIdentifyException extends RuntimeException{
    public CouponRedemptionIdentifyException(String message){
        super(message);
    }
    public CouponRedemptionIdentifyException(){
        super("IDENTIFICADOR DO CUPOM INVALIDO");
    }
}
