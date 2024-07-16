package com.criar.pdi.demonstracao.exceptions.Product.ProductIdentifyException;

public class ProductIdentifyException extends RuntimeException{
    public ProductIdentifyException(String message){
        super(message);
    }
    public ProductIdentifyException(){
        super("IDENTIFICADOR DA LOJA INVALIDO");
    }
}
