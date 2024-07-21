package com.criar.pdi.demonstracao.exceptions.Delivery.DeliveryNotFoundException;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DeliveryNotFoundException extends RuntimeException{
    public DeliveryNotFoundException(String message){
        super(message);
    }
    public DeliveryNotFoundException(){
        super("ENTREGA NAO ENCONTRADA");
    }
}
