package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserCommonDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserUpdateDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.User.UserDuplicateDataException.UserDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.User.UserIdentifyException.UserIdentifyException;
import com.criar.pdi.demonstracao.exceptions.User.UserNotFoundException.UserNotFoundException;
import com.criar.pdi.demonstracao.services.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
            return ResponseEntity.ok(new ResponseBody(200, userService.getUserByID(userID)));
        } catch (UserNotFoundException e){
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.ok(responseBody);
        } catch (UserIdentifyException e){
            return ResponseEntity.ok(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }
    @GetMapping
    public ResponseEntity<?> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ){
        try{
            Page<UserCommonDTO> pages = userService.getUsers(page, size);
            return ResponseEntity.ok(pages);
        } catch (UserNotFoundException e){
            return ResponseEntity.ok(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
    @PostMapping
    public ResponseEntity<?> setUser(@RequestBody @Valid UserDTO userDTO){
        try{
            UserCommonDTO userCommonDTO = userService.setUser(userDTO);
            return ResponseEntity.ok(new ResponseBody(200, userCommonDTO));
        }catch (UserDuplicateDataException e){
            return ResponseEntity.ok(new ResponseBody(409, new MessageDTO(e.getMessage())));
        }
        catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }
    @PutMapping
    @Transactional
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserUpdateDTO userUpdateDTO){
        try{
            UserCommonDTO userCommonDTO = userService.updateUser(userUpdateDTO);
            return ResponseEntity.ok(new ResponseBody(200, userCommonDTO));
        } catch (UserNotFoundException e){
            return ResponseEntity.ok(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body("ERRO NA OPERACAO");
        }
    }
    @DeleteMapping("/{userID}")
    public ResponseEntity<ResponseBody> deleteLogicalUser(
            @PathVariable String userID
    ){
        try{
            userService.deleteUser(userID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO("Usuario inativado com sucesso!!")));
        } catch (UserNotFoundException e){
            return ResponseEntity.ok(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO("Erro ao inativar usuario!!")));
        }
    }
}
