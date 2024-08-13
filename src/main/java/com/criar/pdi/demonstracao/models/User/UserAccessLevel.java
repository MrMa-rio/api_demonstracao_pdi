package com.criar.pdi.demonstracao.models.User;

import java.util.Arrays;
import java.util.Objects;

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


    public static UserAccessLevel get(Integer code){
        if(code.equals(CLIENTE.code())) return CLIENTE;
        if(code.equals(PROPRIETARIO.code())) return PROPRIETARIO;
        if(code.equals(ADMINISTRADOR.code())) return ADMINISTRADOR;  //refatorar
        return null;
    }

    UserAccessLevel(Integer code, String value){
        this.code = code;
        this.value = value;
    }
}
