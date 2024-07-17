package com.criar.pdi.demonstracao.exceptions.Order.OrderGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderGenericException extends RuntimeException{

    public OrderGenericException(){
        super("ERRO AO REALIZAR UMA OPERAÇÃO - PEDIDO");
    }
    public OrderGenericException(String message){
        super(message);
    }

    @ExceptionHandler(OrderGenericException.class)
    public ResponseEntity<ResponseBody> execute(OrderGenericException e){
        return ResponseEntity.ok(new ResponseBody(400, new MessageDTO(e.getMessage())));
    }
}
