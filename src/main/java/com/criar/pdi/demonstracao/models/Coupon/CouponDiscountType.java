package com.criar.pdi.demonstracao.models.Coupon;

public enum CouponDiscountType implements ICouponGenericType {

    PERCENTAGE(1, "PERCENTAGE"),
    FIXED(2, "FIXED");
    private Integer code;
    private String value;
    public Integer code() {
        return code;
    }

    public String value() {
        return value;
    }


    public static CouponDiscountType get(Integer code){

        if(code.equals(PERCENTAGE.code())) return PERCENTAGE;  //refatorar
        if(code.equals(FIXED.code())) return FIXED;
        return null;
    }

    CouponDiscountType(Integer code, String value){
        this.code = code;
        this.value = value;
    }
}
