package com.criar.pdi.demonstracao.exceptions.User.UserGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.User.UserDuplicateDataException.UserDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.User.UserIdentifyException.UserIdentifyException;
import com.criar.pdi.demonstracao.exceptions.User.UserNotFoundException.UserNotFoundException;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseBody> execute(UserNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
    }

    @ExceptionHandler(UserIdentifyException.class)
    public ResponseEntity<ResponseBody> execute(UserIdentifyException e){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
    }
    @ExceptionHandler(UserDuplicateDataException.class)
    public ResponseEntity<ResponseBody> execute(UserDuplicateDataException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseBody(409, new MessageDTO(e.getMessage())));
    }




}
