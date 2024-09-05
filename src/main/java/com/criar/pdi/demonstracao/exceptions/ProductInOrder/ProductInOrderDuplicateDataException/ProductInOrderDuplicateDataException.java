package com.criar.pdi.demonstracao.exceptions.ProductInOrder.ProductInOrderDuplicateDataException;


public class ProductInOrderDuplicateDataException extends RuntimeException{
    public ProductInOrderDuplicateDataException(String message){
        super(message);
    }
    public ProductInOrderDuplicateDataException(){
        super("JA EXISTE UM CADASTRO PARA ESTE PRODUTO NO PEDIDO.");
    }
}
