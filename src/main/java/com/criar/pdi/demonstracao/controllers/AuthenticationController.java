package com.criar.pdi.demonstracao.controllers;

import com.criar.pdi.demonstracao.DTOs.Login.LoginDTO;
import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.DTOs.Token.TokenDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.models.User.User;
import com.criar.pdi.demonstracao.services.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<?> setLogin(@RequestBody @Valid LoginDTO loginDTO) {
        try {
            var token = new UsernamePasswordAuthenticationToken(
                    loginDTO.email(),
                    loginDTO.password()
            );
            Authentication auth = authenticationManager.authenticate(token);
            TokenDTO tokenDTO = new TokenDTO(tokenService.generate((User) auth.getPrincipal()));
            return ResponseEntity.ok(new ResponseBody(200, new MessageDTO(tokenDTO)));
        }catch (InternalAuthenticationServiceException e){
            return ResponseEntity.ok(new ResponseBody(403, new MessageDTO("EMAIL E/OU SENHA INVALIDOS")));
        }
        catch (RuntimeException e){
            return ResponseEntity.ok(new ResponseBody(400, new MessageDTO(e.getMessage())));
        }
    }
}
