package com.criar.pdi.demonstracao.models.Coupon;

import com.criar.pdi.demonstracao.models.User.UserAccessLevel;

public enum CouponType implements ICouponGenericType {
    STORE(0, "STORE"),
    CLIENT(1, "CLIENT"),
    CATEGORY(2, "CATEGORY"),
    EVENT(3, "EVENT");
    private Integer code;
    private String value;
    public Integer code() {
        return code;
    }

    public String value() {
        return value;
    }


    public static CouponType get(Integer code){
        if(code.equals(STORE.code())) return STORE;
        if(code.equals(CLIENT.code())) return CLIENT;
        if(code.equals(CATEGORY.code())) return CATEGORY;  //refatorar
        if(code.equals(EVENT.code())) return EVENT;
        return null;
    }

    CouponType(Integer code, String value){
        this.code = code;
        this.value = value;
    }
}
