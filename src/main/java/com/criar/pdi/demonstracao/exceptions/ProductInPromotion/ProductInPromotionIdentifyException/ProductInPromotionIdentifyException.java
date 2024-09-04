package com.criar.pdi.demonstracao.exceptions.ProductInPromotion.ProductInPromotionIdentifyException;

public class ProductInPromotionIdentifyException extends RuntimeException {
    public ProductInPromotionIdentifyException(String message) {
        super(message);
    }

    public ProductInPromotionIdentifyException() {
        super("IDENTIFICADOR DO PRODUTO EM PROMOCAO INVALIDO");
    }
}
