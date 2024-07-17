package com.criar.pdi.demonstracao.exceptions.Product.ProductNotFoundException;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(String message){
        super(message);
    }
    public ProductNotFoundException(){
        super("PRODUTO NAO ENCONTRADO");
    }
}
