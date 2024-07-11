package com.criar.pdi.demonstracao.exceptions.User.UserNotFoundException;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message){
        super(message);
    }
    public UserNotFoundException(){
        super("USUARIO NAO ENCONTRADO");
    }
}
