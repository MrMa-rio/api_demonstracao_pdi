package com.criar.pdi.demonstracao.exceptions.Address.AddressDuplicateDataException;

public class AddressDuplicateDataException extends RuntimeException{
    public AddressDuplicateDataException(String message){
        super(message);
    }
    public AddressDuplicateDataException(){
        super("JA EXISTE UM ENDEREÇO COM ESTE ID.");
    }
}
