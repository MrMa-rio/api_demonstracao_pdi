package com.criar.pdi.demonstracao.exceptions.Review.ReviewNotFoundException;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(String message){
        super(message);
    }
    public ReviewNotFoundException(){
        super("COMENTARIO NAO ENCONTRADO");
    }
}
