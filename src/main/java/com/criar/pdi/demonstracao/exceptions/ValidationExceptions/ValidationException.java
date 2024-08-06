package com.criar.pdi.demonstracao.exceptions.ValidationExceptions;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.Token.TokenValidationException;
import jakarta.validation.UnexpectedTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
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

        return ResponseEntity.status(401).body(new ResponseBody(401, new MessageDTO("OCORREU UM ERRO NA OPERACAO: " + ex.getMessage())));
    }
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<?> validation(BadCredentialsException ex){

        return ResponseEntity.status(401).body(new ResponseBody(401, new MessageDTO("USUARIO E/OU SENHA INVALIDO")));
    }
    @ExceptionHandler(TokenValidationException.class)
    public ResponseEntity<?> validation(JWTVerificationException ex){

        return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO("OCORREU UM ERRO NA OPERACAO: " + ex.getMessage())));
    }
    @ExceptionHandler(Error.class)
    public ResponseEntity<ResponseBody> validation(Error ex){
        return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO("OCORREU UM ERRO NA OPERACAO: " + ex.getMessage())));
    }

}
