package com.criar.pdi.demonstracao.exceptions.User.UserGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserGenericException extends RuntimeException{

    public UserGenericException(){
        super("ERRO AO REALIZAR UMA OPERAÇÃO - USUARIO");
    }
    public UserGenericException(String message){
        super(message);
    }

    @ExceptionHandler(UserGenericException.class)
    public ResponseEntity<ResponseBody> execute(UserGenericException e){
        return ResponseEntity.ok(new ResponseBody(400, new MessageDTO(e.getMessage())));
    }
}
