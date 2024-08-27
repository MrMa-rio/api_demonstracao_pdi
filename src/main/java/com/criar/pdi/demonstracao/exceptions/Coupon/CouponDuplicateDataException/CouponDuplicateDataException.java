package com.criar.pdi.demonstracao.exceptions.Coupon.CouponDuplicateDataException;

public class CouponDuplicateDataException extends RuntimeException{
    public CouponDuplicateDataException(String message){
        super(message);
    }
    public CouponDuplicateDataException(){
        super("JA EXISTE UM CUPOM COM ESTE ID.");
    }
}
