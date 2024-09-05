package com.criar.pdi.demonstracao.exceptions.ProductInOrder.ProductInOrderNotFoundException;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductInOrderNotFoundException extends RuntimeException{
    public ProductInOrderNotFoundException(String message){
        super(message);
    }
    public ProductInOrderNotFoundException(){
        super("ESTE PRODUTO NAO FOI ENCONTRADO NO PEDIDO ");
    }
}
