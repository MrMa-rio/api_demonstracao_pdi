package com.criar.pdi.demonstracao.config.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.Token.TokenValidationException;
import com.criar.pdi.demonstracao.services.TokenService;
import com.criar.pdi.demonstracao.utils.JSON.JsonConverter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.io.IOException;
import java.nio.file.AccessDeniedException;

@Component
class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, org.springframework.security.access.AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setContentType("application/json");
        response.getOutputStream().print("/teste/teste");
    }
}

@Component
class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    private TokenService tokenService;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.AuthenticationException authException) throws IOException, ServletException {
        tokenService = new TokenService();
        String authorizationHeader = tokenService.getToken(request);
        response.setContentType("application/json");
        try {
            tokenService.verify(authorizationHeader);
            if (!authorizationHeader.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.getOutputStream().print(JsonConverter.toJson(new ResponseBody(HttpServletResponse.SC_FORBIDDEN, new MessageDTO("TOKEN EXPIRADO EM: " + JWT.decode(authorizationHeader).getExpiresAt()))));
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getOutputStream().print(JsonConverter.toJson(new ResponseBody(HttpServletResponse.SC_UNAUTHORIZED, new MessageDTO("ACESSO NEGADO A ESTA ROTA"))));
            }
        } catch (TokenValidationException e){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().print(JsonConverter.toJson(new ResponseBody(HttpServletResponse.SC_UNAUTHORIZED, new MessageDTO("TESTE 2"))));
        }
        catch (RuntimeException e) {
            System.out.println(e.fillInStackTrace());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getOutputStream().print(JsonConverter.toJson(new ResponseBody(HttpServletResponse.SC_UNAUTHORIZED, new MessageDTO("TESTE"))));
        }
    }
}