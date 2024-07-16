package com.criar.pdi.demonstracao.exceptions.Product.ProductDuplicateDataException;

public class ProductDuplicateDataException extends RuntimeException{
    public ProductDuplicateDataException(String message){
        super(message);
    }
    public ProductDuplicateDataException(){
        super("JA EXISTE UM CADASTRO PARA ESTA LOJA.");
    }
}
