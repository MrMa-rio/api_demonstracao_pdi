package com.criar.pdi.demonstracao.exceptions.User.UserDuplicateDataException;

public class UserDuplicateDataException extends RuntimeException{
    public UserDuplicateDataException(String message){
        super(message);
    }
    public UserDuplicateDataException(){
        super("JA EXISTE UM CADASTRO PARA ESTE USUARIO.");
    }
}
