package com.criar.pdi.demonstracao.exceptions.Payment.PaymentIdentifyException;

public class PaymentIdentifyException extends RuntimeException{
    public PaymentIdentifyException(String message){
        super(message);
    }
    public PaymentIdentifyException(){
        super("IDENTIFICADOR DO PAGAMENTO INVALIDO");
    }
}
