package com.criar.pdi.demonstracao.exceptions.Review.ReviewDuplicateDataException;

public class ReviewDuplicateDataException extends RuntimeException{
    public ReviewDuplicateDataException(String message){
        super(message);
    }
    public ReviewDuplicateDataException(){
        super("JA EXISTE UM COMENTARIO COM ESSE IDENTIFICADOR.");
    }
}
