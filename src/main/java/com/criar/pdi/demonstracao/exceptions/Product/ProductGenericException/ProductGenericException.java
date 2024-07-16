package com.criar.pdi.demonstracao.exceptions.Product.ProductGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductGenericException extends RuntimeException{

    public ProductGenericException(){
        super("ERRO AO REALIZAR UMA OPERAÇÃO - LOJA");
    }
    public ProductGenericException(String message){
        super(message);
    }

    @ExceptionHandler(ProductGenericException.class)
    public ResponseEntity<ResponseBody> execute(ProductGenericException e){
        return ResponseEntity.ok(new ResponseBody(400, new MessageDTO(e.getMessage())));
    }
}
