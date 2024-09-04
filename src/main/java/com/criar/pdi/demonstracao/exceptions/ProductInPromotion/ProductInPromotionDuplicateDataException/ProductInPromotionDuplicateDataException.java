package com.criar.pdi.demonstracao.exceptions.ProductInPromotion.ProductInPromotionDuplicateDataException;

public class ProductInPromotionDuplicateDataException extends RuntimeException {
    public ProductInPromotionDuplicateDataException(String message){
        super(message);
    }
    public ProductInPromotionDuplicateDataException(){
        super("JA EXISTE UM REGISTRO PARA ESTE PRODUTO EM PROMOCAO.");
    }
}
