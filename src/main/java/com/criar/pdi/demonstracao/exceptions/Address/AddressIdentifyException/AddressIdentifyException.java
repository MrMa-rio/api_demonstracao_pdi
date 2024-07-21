package com.criar.pdi.demonstracao.exceptions.Address.AddressIdentifyException;

public class AddressIdentifyException extends RuntimeException{
    public AddressIdentifyException(String message){
        super(message);
    }
    public AddressIdentifyException(){
        super("IDENTIFICADOR DO ENDEREÇO INVALIDO");
    }
}
