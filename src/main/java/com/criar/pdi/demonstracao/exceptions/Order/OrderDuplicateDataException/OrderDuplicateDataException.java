package com.criar.pdi.demonstracao.exceptions.Order.OrderDuplicateDataException;

public class OrderDuplicateDataException extends RuntimeException{
    public OrderDuplicateDataException(String message){
        super(message);
    }
    public OrderDuplicateDataException(){
        super("JA EXISTE UM PEDIDO COM ESTE ID.");
    }
}
