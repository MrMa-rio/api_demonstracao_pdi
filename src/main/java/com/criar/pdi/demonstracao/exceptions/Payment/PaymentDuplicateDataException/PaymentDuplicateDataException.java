package com.criar.pdi.demonstracao.exceptions.Payment.PaymentDuplicateDataException;

public class PaymentDuplicateDataException extends RuntimeException{
    public PaymentDuplicateDataException(String message){
        super(message);
    }
    public PaymentDuplicateDataException(){
        super("JA EXISTE UM PAGAMENTO EM ANDAMENTO.");
    }
}
