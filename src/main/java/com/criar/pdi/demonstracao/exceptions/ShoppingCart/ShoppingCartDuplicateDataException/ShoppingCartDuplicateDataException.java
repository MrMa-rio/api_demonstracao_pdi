package com.criar.pdi.demonstracao.exceptions.ShoppingCart.ShoppingCartDuplicateDataException;

public class ShoppingCartDuplicateDataException extends RuntimeException{
    public ShoppingCartDuplicateDataException(String message){
        super(message);
    }
    public ShoppingCartDuplicateDataException(){
        super("JA EXISTE UM CADASTRO PARA ESTE CARRINHO DE COMPRAS.");
    }
}
