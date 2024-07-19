package com.criar.pdi.demonstracao.exceptions.Payment.PaymentNotFoundException;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentNotFoundException extends RuntimeException{
    public PaymentNotFoundException(String message){
        super(message);
    }
    public PaymentNotFoundException(){
        super("PAGAMENTO NAO ENCONTRADO");
    }
}
