package com.criar.pdi.demonstracao.exceptions.Store.StoreIdentifyException;

public class StoreIdentifyException extends RuntimeException{
    public StoreIdentifyException(String message){
        super(message);
    }
    public StoreIdentifyException(){
        super("IDENTIFICADOR DA LOJA INVALIDO");
    }
}
