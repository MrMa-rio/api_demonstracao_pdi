package com.criar.pdi.demonstracao.exceptions.ProductInPromotion.ProductInPromotionGenericException;


import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductInPromotionGenericException extends RuntimeException {

    public ProductInPromotionGenericException() {
        super("ERRO AO REALIZAR UMA OPERAÇÃO - REGISTRO DE CUPOM");
    }

    public ProductInPromotionGenericException(String message) {
        super(message);
    }

    @ExceptionHandler(ProductInPromotionGenericException.class)
    public ResponseEntity<ResponseBody> execute(ProductInPromotionGenericException e) {
        return ResponseEntity.status(400).body(new ResponseBody(400, new MessageDTO(e.getMessage())));
    }
}
