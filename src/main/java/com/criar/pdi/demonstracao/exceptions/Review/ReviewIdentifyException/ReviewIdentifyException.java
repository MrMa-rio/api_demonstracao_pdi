package com.criar.pdi.demonstracao.exceptions.Review.ReviewIdentifyException;

public class ReviewIdentifyException extends RuntimeException{
    public ReviewIdentifyException(String message){
        super(message);
    }
    public ReviewIdentifyException(){
        super("IDENTIFICADOR DO COMENTARIO INVALIDO");
    }
}
