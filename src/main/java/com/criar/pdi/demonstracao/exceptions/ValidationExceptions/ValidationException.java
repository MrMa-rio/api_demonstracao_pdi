package com.criar.pdi.demonstracao.exceptions.ValidationExceptions;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> validation(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ResponseEntity.ok(new ResponseBody(400, new MessageDTO(errors)));
    }

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    public ResponseEntity<?> validation(InternalAuthenticationServiceException ex){

        return ResponseEntity.ok(new ResponseBody(400, new MessageDTO("ERRO")));
    }
}
