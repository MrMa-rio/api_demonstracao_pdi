package com.criar.pdi.demonstracao.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/{userID}")
    public ResponseEntity<?> getUser(@PathVariable String userID){
        return ResponseEntity.ok("Retorno OK" + userID);
    }
}
