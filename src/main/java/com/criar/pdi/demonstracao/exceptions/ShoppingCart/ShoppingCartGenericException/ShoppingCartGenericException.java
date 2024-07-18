package com.criar.pdi.demonstracao.exceptions.ShoppingCart.ShoppingCartGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShoppingCartGenericException extends RuntimeException{

    public ShoppingCartGenericException(){
        super("ERRO AO REALIZAR UMA OPERAÇÃO - CARRINHO DE COMPRAS");
    }
    public ShoppingCartGenericException(String message){
        super(message);
    }

    @ExceptionHandler(ShoppingCartGenericException.class)
    public ResponseEntity<ResponseBody> execute(ShoppingCartGenericException e){
        return ResponseEntity.ok(new ResponseBody(400, new MessageDTO(e.getMessage())));
    }
}
