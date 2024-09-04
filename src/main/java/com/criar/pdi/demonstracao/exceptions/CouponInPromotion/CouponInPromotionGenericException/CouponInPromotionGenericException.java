package com.criar.pdi.demonstracao.exceptions.CouponInPromotion.CouponInPromotionGenericException;


import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponInPromotionGenericException extends RuntimeException {

    public CouponInPromotionGenericException() {
        super("ERRO AO REALIZAR UMA OPERAÇÃO - REGISTRO DE CUPOM");
    }

    public CouponInPromotionGenericException(String message) {
        super(message);
    }

    @ExceptionHandler(CouponInPromotionGenericException.class)
    public ResponseEntity<ResponseBody> execute(CouponInPromotionGenericException e) {
        return ResponseEntity.ok(new ResponseBody(400, new MessageDTO(e.getMessage())));
    }
}
