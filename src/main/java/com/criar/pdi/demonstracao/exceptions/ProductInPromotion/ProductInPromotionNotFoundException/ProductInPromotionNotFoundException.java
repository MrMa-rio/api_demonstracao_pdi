package com.criar.pdi.demonstracao.exceptions.ProductInPromotion.ProductInPromotionNotFoundException;

public class ProductInPromotionNotFoundException extends RuntimeException{
    public ProductInPromotionNotFoundException(String message){
        super(message);
    }
    public ProductInPromotionNotFoundException(){
        super("ESTE PRODUTO EM PROMOCAO NAO FOI ENCONTRADO ");
    }
}