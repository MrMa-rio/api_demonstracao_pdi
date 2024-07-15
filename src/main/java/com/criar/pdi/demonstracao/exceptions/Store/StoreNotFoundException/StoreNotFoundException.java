package com.criar.pdi.demonstracao.exceptions.Store.StoreNotFoundException;

import com.criar.pdi.demonstracao.exceptions.Store.StoreGenericException.StoreGenericException;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
