package com.criar.pdi.demonstracao.exceptions.Order.OrderNotFoundException;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String message){
        super(message);
    }
    public OrderNotFoundException(){
        super("PEDIDO NAO ENCONTRADO");
    }
}
