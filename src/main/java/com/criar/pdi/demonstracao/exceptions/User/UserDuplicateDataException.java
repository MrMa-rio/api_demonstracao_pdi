package com.criar.pdi.demonstracao.exceptions.User;

public class UserDuplicateDataException extends RuntimeException{
    public UserDuplicateDataException(String message){
        super(message);
    }
    public UserDuplicateDataException(){
        super("JA EXISTE UM CADASTRO PARA ESTE USUARIO.");
    }
}
