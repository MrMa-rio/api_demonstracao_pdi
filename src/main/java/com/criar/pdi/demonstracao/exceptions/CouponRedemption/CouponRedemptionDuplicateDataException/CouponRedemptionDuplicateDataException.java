package com.criar.pdi.demonstracao.exceptions.CouponRedemption.CouponRedemptionDuplicateDataException;

public class CouponRedemptionDuplicateDataException extends RuntimeException{
    public CouponRedemptionDuplicateDataException(String message){
        super(message);
    }
    public CouponRedemptionDuplicateDataException(){
        super("JA EXISTE UM CUPOM COM ESTE ID.");
    }
}
