package com.criar.pdi.demonstracao.exceptions.Delivery.DeliveryIdentifyException;

public class DeliveryIdentifyException extends RuntimeException{
    public DeliveryIdentifyException(String message){
        super(message);
    }
    public DeliveryIdentifyException(){
        super("IDENTIFICADOR DA ENTREGA INVALIDA");
    }
}
