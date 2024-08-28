package com.criar.pdi.demonstracao.exceptions.Delivery.DeliveryGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DeliveryGenericException extends RuntimeException{

    public DeliveryGenericException(){
        super("ERRO AO REALIZAR UMA OPERAÇÃO - ENTREGA");
    }
    public DeliveryGenericException(String message){
        super(message);
    }

    @ExceptionHandler(DeliveryGenericException.class)
    public ResponseEntity<ResponseBody> execute(DeliveryGenericException e){
        return ResponseEntity.ok(new ResponseBody(400, new MessageDTO(e.getMessage())));
    }
}
