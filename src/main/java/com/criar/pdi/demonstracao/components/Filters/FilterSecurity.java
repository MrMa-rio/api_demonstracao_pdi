package com.criar.pdi.demonstracao.components.Filters;

import com.auth0.jwt.interfaces.Claim;
import com.criar.pdi.demonstracao.repositories.IUserRepository;
import com.criar.pdi.demonstracao.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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

        String authorizationHeader = getToken(request);
        if(!authorizationHeader.isEmpty()) {
            String subject = tokenService.getSubject(authorizationHeader);
            UserDetails userDetails = iUserRepository.findByEmail(subject);
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(request, response);
    }
    public String getToken(HttpServletRequest request){

        String authorizationToken = request.getHeader("Authorization");
        if(authorizationToken != null){
            return authorizationToken.replace("Bearer", "");
        }
        return "";
    }
}
