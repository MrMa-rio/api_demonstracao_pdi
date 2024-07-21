package com.criar.pdi.demonstracao.exceptions.Address.AddressNotFoundException;

import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(String message){
        super(message);
    }
    public AddressNotFoundException(){
        super("ENDEREÇO NAO ENCONTRADO");
    }
}
