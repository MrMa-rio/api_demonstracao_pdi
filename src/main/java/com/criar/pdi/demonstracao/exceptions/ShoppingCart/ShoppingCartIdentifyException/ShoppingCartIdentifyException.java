package com.criar.pdi.demonstracao.exceptions.ShoppingCart.ShoppingCartIdentifyException;

public class ShoppingCartIdentifyException extends RuntimeException{
    public ShoppingCartIdentifyException(String message){
        super(message);
    }
    public ShoppingCartIdentifyException(){
        super("IDENTIFICADOR DO CARRINHO DE COMPRAS INVALIDO");
    }
}
