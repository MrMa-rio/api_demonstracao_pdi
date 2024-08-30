package com.criar.pdi.demonstracao.models.Coupon;

public enum CouponCreateType implements ICouponGenericType {
    CLIENT(1, "CLIENT"),
    OWNER(2, "OWNER"),
    ADMIN(3, "ADMIN");
    private Integer code;
    private String value;
    public Integer code() {
        return code;
    }

    public String value() {
        return value;
    }


    public static CouponCreateType get(Integer code){
        if(code.equals(CLIENT.code())) return CLIENT;
        if(code.equals(OWNER.code())) return OWNER;  //refatorar
        if(code.equals(ADMIN.code())) return ADMIN;
        return null;
    }

    CouponCreateType(Integer code, String value){
        this.code = code;
        this.value = value;
    }
}
