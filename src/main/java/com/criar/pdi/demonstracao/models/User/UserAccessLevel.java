package com.criar.pdi.demonstracao.models.User;

public enum UserAccessLevel {
    CLIENTE(0, "CLIENTE"),
    PROPRIETARIO(1, "PROPRIETARIO"),
    ADMINISTRADOR(2, "ADMINISTRADOR");
    private Integer code;
    private String value;
    public Integer code() {
        return code;
    }

    public String value() {
        return value;
    }




    UserAccessLevel(Integer code, String value){
        this.code = code;
        this.value = value;
    }
}
