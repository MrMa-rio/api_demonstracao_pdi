package com.criar.pdi.demonstracao.exceptions.Store.StoreGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.Store.StoreDuplicateDataException.StoreDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreIdentifyException.StoreIdentifyException;
import com.criar.pdi.demonstracao.exceptions.Store.StoreNotFoundException.StoreNotFoundException;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<ResponseBody> execute(StoreNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
    }

    @ExceptionHandler(StoreIdentifyException.class)
    public ResponseEntity<ResponseBody> execute(StoreIdentifyException e){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
    }
    @ExceptionHandler(StoreDuplicateDataException.class)
    public ResponseEntity<ResponseBody> execute(StoreDuplicateDataException e){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ResponseBody(409, new MessageDTO(e.getMessage())));
    }
}
