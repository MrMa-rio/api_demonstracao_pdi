package com.criar.pdi.demonstracao.exceptions.Payment.PaymentGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentGenericException extends RuntimeException{

    public PaymentGenericException(){
        super("ERRO AO REALIZAR UMA OPERAÇÃO - PAGAMENTO");
    }
    public PaymentGenericException(String message){
        super(message);
    }

    @ExceptionHandler(PaymentGenericException.class)
    public ResponseEntity<ResponseBody> execute(PaymentGenericException e){
        return ResponseEntity.ok(new ResponseBody(400, new MessageDTO(e.getMessage())));
    }
}
