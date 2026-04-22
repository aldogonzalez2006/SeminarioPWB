package com.product.jwt;

import org.springframework.web.filter.OncePerRequestFilter;
import com.product.api.service.DefaultUserAuthentication;
import com.product.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.IOException;

public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private DefaultUserAuthentication userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        String username = null;
        String token    = null;

        // 1. Extraer token
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token    = authHeader.substring(7);
            username = jwtUtil.extractUsername(token);
        }

        // 2. Validar y poner en SecurityContext
        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            UserDetails userDetails =
                    userDetailsService.loadUserByUsername(username);

            if (jwtUtil.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities()
                        );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 3. Continuar
        filterChain.doFilter(request, response);
    }
}
