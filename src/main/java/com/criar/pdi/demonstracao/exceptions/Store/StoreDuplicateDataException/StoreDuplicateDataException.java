package com.criar.pdi.demonstracao.exceptions.Store.StoreDuplicateDataException;

public class StoreDuplicateDataException extends RuntimeException{
    public StoreDuplicateDataException(String message){
        super(message);
    }
    public StoreDuplicateDataException(){
        super("JA EXISTE UM CADASTRO PARA ESTA LOJA.");
    }
}
