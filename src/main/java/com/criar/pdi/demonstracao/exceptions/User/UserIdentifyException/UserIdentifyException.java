package com.criar.pdi.demonstracao.exceptions.User.UserIdentifyException;

public class UserIdentifyException extends RuntimeException{
    public UserIdentifyException(String message){
        super(message);
    }
    public UserIdentifyException(){
        super("IDENTIFICADOR DE USUARIO INVALIDO");
    }
}
