package com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductInCartGenericException extends RuntimeException{

    public ProductInCartGenericException(){
        super("ERRO AO REALIZAR UMA OPERAÇÃO - PRODUTO NO CARRINHO");
    }
    public ProductInCartGenericException(String message){
        super(message);
    }

    @ExceptionHandler(ProductInCartGenericException.class)
    public ResponseEntity<ResponseBody> execute(ProductInCartGenericException e){
        return ResponseEntity.ok(new ResponseBody(400, new MessageDTO(e.getMessage())));
    }
}
