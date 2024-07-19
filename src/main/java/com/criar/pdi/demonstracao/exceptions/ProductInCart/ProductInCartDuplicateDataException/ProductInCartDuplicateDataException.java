package com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartDuplicateDataException;

public class ProductInCartDuplicateDataException extends RuntimeException{
    public ProductInCartDuplicateDataException(String message){
        super(message);
    }
    public ProductInCartDuplicateDataException(){
        super("JA EXISTE UM CADASTRO PARA ESTE PRODUTO NO CARRINHO.");
    }
}
