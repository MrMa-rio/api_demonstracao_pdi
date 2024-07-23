package com.criar.pdi.demonstracao.models.User;

public enum UserAccessLevel {
    CLIENTE(0, "CLIENTE"),
    PROPRIETARIO(1, "PROPRIETARIO"),
    ADMINISTRADOR(2, "ADMINISTRADOR");
    private Integer code;

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    private String value;


    UserAccessLevel(Integer code, String value){
        this.code = code;
        this.value = value;
    }
}
