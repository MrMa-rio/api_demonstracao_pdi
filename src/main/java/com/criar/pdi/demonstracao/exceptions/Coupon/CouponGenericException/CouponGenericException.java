package com.criar.pdi.demonstracao.exceptions.Coupon.CouponGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponGenericException extends RuntimeException {

    public CouponGenericException() {
        super("ERRO AO REALIZAR UMA OPERAÇÃO - CUPOM");
    }

    public CouponGenericException(String message) {
        super(message);
    }

    @ExceptionHandler(CouponGenericException.class)
    public ResponseEntity<ResponseBody> execute(CouponGenericException e) {
        return ResponseEntity.status(400).body(new ResponseBody(400, new MessageDTO(e.getMessage())));
    }
}
