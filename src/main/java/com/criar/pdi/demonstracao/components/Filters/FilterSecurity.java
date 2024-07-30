package com.criar.pdi.demonstracao.components.Filters;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.criar.pdi.demonstracao.DTOs.Message.MessageDTO;
import com.criar.pdi.demonstracao.components.ResponseBody.ResponseBody;
import com.criar.pdi.demonstracao.exceptions.Token.TokenValidationException;
import com.criar.pdi.demonstracao.repositories.IUserRepository;
import com.criar.pdi.demonstracao.services.TokenService;
import com.criar.pdi.demonstracao.utils.JSON.JsonConverter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterSecurity extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private IUserRepository iUserRepository;

    @Override
    protected void doFilterInternal(
            @NotNull HttpServletRequest request,
            @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain
    ) throws ServletException, IOException {
        String authorizationHeader = tokenService.getToken(request);
        if (!authorizationHeader.isEmpty()) {
            String subject = tokenService.getSubject(authorizationHeader);
            UserDetails userDetails = iUserRepository.findByEmail(subject);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
}
