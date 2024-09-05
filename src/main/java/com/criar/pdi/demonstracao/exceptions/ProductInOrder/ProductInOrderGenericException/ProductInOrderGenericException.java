package com.criar.pdi.demonstracao.exceptions.ProductInOrder.ProductInOrderGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.ProductInCart.ProductInCartGenericException.ProductInCartGenericException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductInOrderGenericException extends RuntimeException{

    public ProductInOrderGenericException(){
        super("ERRO AO REALIZAR UMA OPERAÇÃO - PRODUTO NO CARRINHO");
    }
    public ProductInOrderGenericException(String message){
        super(message);
    }

    @ExceptionHandler(ProductInOrderGenericException.class)
    public ResponseEntity<ResponseBody> execute(ProductInCartGenericException e){
        return ResponseEntity.status(400).body(new ResponseBody(400, new MessageDTO(e.getMessage())));
    }
}