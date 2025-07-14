package com.alasdeplata.config.filter;

import java.io.IOException;
import java.util.Collection;

import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alasdeplata.common.JwtUtils;
import com.alasdeplata.models.UserEntity;
import com.alasdeplata.repository.UserRepository;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;

    public JwtTokenValidator(JwtUtils jwtUtils, UserRepository userRepository) {
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws IOException, ServletException {

        String path = request.getRequestURI();

        if (
                path.startsWith("/api/v1/auth/") ||
                        (path.equals("/api/v1/contacto") && request.getMethod().equalsIgnoreCase("POST")) ||
                        (path.equals("/api/chat") && request.getMethod().equalsIgnoreCase("POST"))
        ) {
            filterChain.doFilter(request, response);
            return;
        }


        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (jwtToken != null && jwtToken.startsWith("Bearer ")) {
            jwtToken = jwtToken.substring(7);

            try {
                DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);
                String username = jwtUtils.extractUsername(decodedJWT);

                UserEntity user = userRepository.findUserEntityByUsername(username)
                        .orElse(null);

                if (user == null || !user.isEnabled()) {
                    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token no válido");
                    return;
                }

                String stringAuthorities = jwtUtils.getSpecificClaim(decodedJWT, "authorities").asString();

                Collection<? extends GrantedAuthority> authorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList(stringAuthorities);

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
                        null, authorities);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (JWTVerificationException e) {
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token no válido");
            }

        }

        filterChain.doFilter(request, response);
    }

}
