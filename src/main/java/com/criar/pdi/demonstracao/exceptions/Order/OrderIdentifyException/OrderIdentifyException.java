package com.criar.pdi.demonstracao.exceptions.Order.OrderIdentifyException;

public class OrderIdentifyException extends RuntimeException{
    public OrderIdentifyException(String message){
        super(message);
    }
    public OrderIdentifyException(){
        super("IDENTIFICADOR DO PEDIDO INVALIDO");
    }
}
