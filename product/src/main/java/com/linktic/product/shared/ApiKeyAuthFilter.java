package com.linktic.product.shared;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class ApiKeyAuthFilter extends OncePerRequestFilter {

    private final String apiSecretKey;
    private final String authHeaderName = "X-API-KEY";

    public ApiKeyAuthFilter(String apiSecretKey) {
        this.apiSecretKey = apiSecretKey;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String requestApiKey = request.getHeader(authHeaderName);

        if (requestApiKey != null && requestApiKey.equals(apiSecretKey)) {
            PreAuthenticatedAuthenticationToken authentication =
                    new PreAuthenticatedAuthenticationToken(requestApiKey, null, null);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else if (requestApiKey != null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("API Key Inválida.");
            log.error("API Key Inválida.");
            return;
        }

        filterChain.doFilter(request, response);
    }
}