package com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartNotFoundException;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductInCartNotFoundException extends RuntimeException{
    public ProductInCartNotFoundException(String message){
        super(message);
    }
    public ProductInCartNotFoundException(){
        super("ESTE PRODUTO NAO FOI ENCONTRADO NO CARRINHO ");
    }
}
