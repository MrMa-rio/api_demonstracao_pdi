package com.criar.pdi.demonstracao.exceptions.CouponRedemption.CouponRedemptionGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CouponRedemptionGenericException extends RuntimeException{

    public CouponRedemptionGenericException(){
        super("ERRO AO REALIZAR UMA OPERAÇÃO - REGISTRO DE CUPOM");
    }
    public CouponRedemptionGenericException(String message){
        super(message);
    }

    @ExceptionHandler(CouponRedemptionGenericException.class)
    public ResponseEntity<ResponseBody> execute(CouponRedemptionGenericException e){
        return ResponseEntity.ok(new ResponseBody(400, new MessageDTO(e.getMessage())));
    }
}
