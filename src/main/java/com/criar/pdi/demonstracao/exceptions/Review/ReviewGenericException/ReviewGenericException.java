package com.criar.pdi.demonstracao.exceptions.Review.ReviewGenericException;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReviewGenericException extends RuntimeException{

    public ReviewGenericException(){
        super("ERRO AO REALIZAR UMA OPERAÇÃO - AVALIAÇÃO PRODUTO");
    }
    public ReviewGenericException(String message){
        super(message);
    }

    @ExceptionHandler(ReviewGenericException.class)
    public ResponseEntity<ResponseBody> execute(ReviewGenericException e){
        return ResponseEntity.ok(new ResponseBody(400, new MessageDTO(e.getMessage())));
    }
}
