package com.criar.pdi.demonstracao.exceptions.Store.StoreNotFoundException;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StoreNotFoundException extends RuntimeException{
    public StoreNotFoundException(String message){
        super(message);
    }
    public StoreNotFoundException(){
        super("LOJA NAO ENCONTRADA");
    }
}
