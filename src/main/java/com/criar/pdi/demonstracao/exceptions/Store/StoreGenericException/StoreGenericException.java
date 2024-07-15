package com.criar.pdi.demonstracao.exceptions.Store.StoreGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StoreGenericException extends RuntimeException{

    public StoreGenericException(){
        super("ERRO AO REALIZAR UMA OPERAÇÃO - LOJA");
    }
    public StoreGenericException(String message){
        super(message);
    }

    @ExceptionHandler(StoreGenericException.class)
    public ResponseEntity<ResponseBody> execute(StoreGenericException e){
        return ResponseEntity.ok(new ResponseBody(400, new MessageDTO(e.getMessage())));
    }
}
