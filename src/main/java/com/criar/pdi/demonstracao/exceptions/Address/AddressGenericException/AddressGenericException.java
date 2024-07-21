package com.criar.pdi.demonstracao.exceptions.Address.AddressGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AddressGenericException extends RuntimeException{

    public AddressGenericException(){
        super("ERRO AO REALIZAR UMA OPERAÇÃO - ENDEREÇO");
    }
    public AddressGenericException(String message){
        super(message);
    }

    @ExceptionHandler(AddressGenericException.class)
    public ResponseEntity<ResponseBody> execute(AddressGenericException e){
        return ResponseEntity.ok(new ResponseBody(400, new MessageDTO(e.getMessage())));
    }
}
