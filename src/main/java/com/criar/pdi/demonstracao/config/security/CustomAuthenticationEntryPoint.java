package com.criar.pdi.demonstracao.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.services.TokenService;
import com.criar.pdi.demonstracao.utils.JSON.JsonConverter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private final TokenService tokenService;

    @Autowired
    CustomAuthenticationEntryPoint(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String authorizationHeader = tokenService.getToken(request);
        response.setContentType("application/json");

        try {
            tokenService.verify(authorizationHeader);
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().print(JsonConverter.toJson(new ResponseBody(HttpServletResponse.SC_UNAUTHORIZED, new MessageDTO("ACESSO NEGADO A ESTA ROTA"))));
        } catch (TokenExpiredException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getOutputStream().print(JsonConverter.toJson(new ResponseBody(HttpServletResponse.SC_FORBIDDEN, new MessageDTO("TOKEN EXPIRADO EM: " + JWT.decode(authorizationHeader).getExpiresAt()))));
        } catch (JWTDecodeException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().print(JsonConverter.toJson(new ResponseBody(HttpServletResponse.SC_UNAUTHORIZED, new MessageDTO("ERRO NA AUTENTICACAO: TOKEN VAZIO OU INVALIDO"))));
        } catch (SignatureVerificationException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().print(JsonConverter.toJson(new ResponseBody(HttpServletResponse.SC_UNAUTHORIZED, new MessageDTO("ASSINATURA DO TOKEN INVALIDA"))));
        } catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().print(JsonConverter.toJson(new ResponseBody(HttpServletResponse.SC_UNAUTHORIZED, new MessageDTO(e.getMessage().toUpperCase()))));
        }
    }
}