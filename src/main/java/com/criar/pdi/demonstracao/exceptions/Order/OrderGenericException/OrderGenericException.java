package com.criar.pdi.demonstracao.exceptions.Order.OrderGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.Order.OrderDuplicateDataException.OrderDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Order.OrderIdentifyException.OrderIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Order.OrderNotFoundException.OrderNotFoundException;
import org.springframework.http.HttpStatus;
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


    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<ResponseBody> execute(OrderNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
    }

    @ExceptionHandler(OrderIdentifyException.class)
    public ResponseEntity<ResponseBody> execute(OrderIdentifyException e){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
    }
    @ExceptionHandler(OrderDuplicateDataException.class)
    public ResponseEntity<ResponseBody> execute(OrderDuplicateDataException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseBody(409, new MessageDTO(e.getMessage())));
    }
}
