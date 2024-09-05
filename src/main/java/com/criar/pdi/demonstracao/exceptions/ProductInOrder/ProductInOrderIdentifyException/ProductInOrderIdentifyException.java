package com.criar.pdi.demonstracao.exceptions.ProductInOrder.ProductInOrderIdentifyException;

public class ProductInOrderIdentifyException extends RuntimeException{
    public ProductInOrderIdentifyException(String message){
        super(message);
    }
    public ProductInOrderIdentifyException(){
        super("IDENTIFICADOR DO PRODUTO NO PEDIDO INVALIDO");
    }
}
