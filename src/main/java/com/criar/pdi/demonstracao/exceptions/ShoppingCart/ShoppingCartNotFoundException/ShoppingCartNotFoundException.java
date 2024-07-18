package com.criar.pdi.demonstracao.exceptions.ShoppingCart.ShoppingCartNotFoundException;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShoppingCartNotFoundException extends RuntimeException {
    public ShoppingCartNotFoundException(String message){
        super(message);
    }
    public ShoppingCartNotFoundException(){
        super("CARRINHO DE COMPRAS NAO ENCONTRADO");
    }
}
