package com.criar.pdi.demonstracao.exceptions.Delivery.DeliveryDuplicateDataException;

public class DeliveryDuplicateDataException extends RuntimeException{
    public DeliveryDuplicateDataException(String message){
        super(message);
    }
    public DeliveryDuplicateDataException(){
        super("JA EXISTE UMA ENTREGA COM ESTE ID.");
    }
}
