package com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartIdentifyException;

public class ProductInCartIdentifyException extends RuntimeException{
    public ProductInCartIdentifyException(String message){
        super(message);
    }
    public ProductInCartIdentifyException(){
        super("IDENTIFICADOR DO PRODUTO NO CARRINHO INVALIDO");
    }
}
