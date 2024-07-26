package com.criar.pdi.demonstracao.exceptions.Token;

public class TokenValidationException extends RuntimeException{
    public TokenValidationException(){
        super("OCORREU UM ERRO NA OPERAÇÃO QUE VALIDA O TOKEN");
    }
    public TokenValidationException(String message){
        super(message);
    }
}
