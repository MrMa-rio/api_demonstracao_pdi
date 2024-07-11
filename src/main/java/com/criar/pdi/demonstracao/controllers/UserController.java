package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.User.UserDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.User.UserDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.User.UserIdentifyException.UserIdentifyException;
import com.criar.pdi.demonstracao.exceptions.User.UserNotFoundException.UserNotFoundException;
import com.criar.pdi.demonstracao.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userID}")
    public ResponseEntity<?> getUser(@PathVariable String userID){
        try{
            return ResponseEntity.ok(userService.getUserByID(userID));
        } catch (UserNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, e.getMessage());
            return ResponseEntity.ok(responseBody);
        } catch (UserIdentifyException e){
            return ResponseEntity.ok(new ResponseBody(422, e.getMessage()));
        }
    }
    @PostMapping
    public ResponseEntity<?> setUser(@RequestBody @Valid UserDTO userDTO){
        try{
            userService.setUser(userDTO);
            return ResponseEntity.ok("Usuario Criado com Sucesso!!");
        }catch (UserDuplicateDataException e){
            return ResponseEntity.ok(new ResponseBody(409, e.getMessage()));
        }catch (RuntimeException e){
            throw new RuntimeException(e);
        }



    }
    @PutMapping
    @Transactional
    public ResponseEntity<?> updateUser(@PathVariable @Valid UserDTO userDTO){
        return ResponseEntity.ok("Usuario Atualizado com Sucesso!!");
    }
    @DeleteMapping("/{userID}")
    public ResponseEntity<?> deleteLogicalUser(@PathVariable String userID){
        return ResponseEntity.ok("Usuario inativado com sucesso!");
    }
}
