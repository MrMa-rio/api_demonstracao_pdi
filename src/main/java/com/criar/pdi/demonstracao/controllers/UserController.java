package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserCommonDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserSearchDTO;
import com.criar.pdi.demonstracao.DTOs.User.UserUpdateDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.User.UserDuplicateDataException.UserDuplicateDataException;
import com.criar.pdi.demonstracao.exceptions.User.UserIdentifyException.UserIdentifyException;
import com.criar.pdi.demonstracao.exceptions.User.UserNotFoundException.UserNotFoundException;
import com.criar.pdi.demonstracao.services.UserService;
import com.criar.pdi.demonstracao.specifications.Users.UserSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userID}")
    @Operation(description = "Pega um Usuario atraves do ID")
    public ResponseEntity<?> getUser(@PathVariable String userID) {
        try {
            return ResponseEntity.ok(new ResponseBody(200, userService.getUserByID(userID)));
        } catch (UserNotFoundException e) {
            ResponseBody responseBody = new ResponseBody(404, new MessageDTO(e.getMessage()));
            return ResponseEntity.status(404).body(responseBody);
        } catch (UserIdentifyException e) {
            return ResponseEntity.status(422).body(new ResponseBody(422, new MessageDTO(e.getMessage())));
        }
    }

    @GetMapping
    @Operation(description = "Pega uma lista paginada de Usuarios")
    public ResponseEntity<?> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        try {
            Page<UserCommonDTO> pages = userService.getUsers(page, size);
            return ResponseEntity.ok(pages);
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }

    @GetMapping("/search")
    @Operation(description = "Pega uma lista paginada de Usuarios por Parametros")
    @Transactional
    public ResponseEntity<?> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "", required = false) String name,
            @RequestParam(defaultValue = "", required = false) String fullName,
            @RequestParam(defaultValue = "", required = false) String email,
            @RequestParam(defaultValue = "", required = false) String cpf,
            @RequestParam(defaultValue = "", required = false) Integer userAccessLevel
    ) {
        try {
            Page<UserCommonDTO> pages = userService.getUsersByParams(new UserSpecification(new UserSearchDTO(
                    name,
                    fullName,
                    email,
                    cpf,
                    userAccessLevel)), page, size);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO(pages)));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }

    @PostMapping
    @Operation(description = "Cria um novo Usuario")
    public ResponseEntity<?> setUser(@RequestBody @Valid UserDTO userDTO) {
        try {
            UserCommonDTO userCommonDTO = userService.setUser(userDTO);
            return ResponseEntity.ok(new ResponseBody(200, userCommonDTO));
        } catch (UserDuplicateDataException e) {
            return ResponseEntity.status(409).body(new ResponseBody(409, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping
    @Transactional
    @Operation(description = "Atualiza um Usuario")
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        try {
            UserCommonDTO userCommonDTO = userService.updateUser(userUpdateDTO);
            return ResponseEntity.ok(new ResponseBody(200, userCommonDTO));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body("ERRO NA OPERACAO");
        }
    }

    @DeleteMapping("/{userID}")
    @Operation(description = "Inativa um Usuario atraves do ID")
    public ResponseEntity<ResponseBody> deleteLogicalUser(
            @PathVariable String userID
    ) {
        try {
            userService.deleteUser(userID);
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO("Usuario inativado com sucesso!!")));
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(404).body(new ResponseBody(404, new MessageDTO(e.getMessage())));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ResponseBody(400, new MessageDTO("Erro ao inativar usuario!!")));
        }
    }
}
