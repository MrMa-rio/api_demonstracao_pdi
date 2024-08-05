package com.criar.pdi.demonstracao.controllers;


import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
public class HelloController {
    @GetMapping
    @SecurityRequirement(name = "bearer-key")
    public ResponseEntity<String> helloWorld(){
        return ResponseEntity.ok("Hello World!!") ;
    }
}
