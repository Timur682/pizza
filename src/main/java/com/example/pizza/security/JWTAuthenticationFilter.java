package com.example.pizza.security;

import com.example.pizza.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final CustomUserDetailsService userDetailsService;
    private final JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {
        //get jwt from request
        var token = getJwtFromRequest(request);
        //validate the token, only if it exists
        if (StringUtils.hasText(token) && tokenProvider.validateToken(token)){
            //get username from token
            var username = tokenProvider.getUsernameFromToken(token);

            //load the user from customUserDetailsService (DB)
            UserDetails user = userDetailsService.loadUserByUsername(username);

            var authentication = new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    null,
                    user.getAuthorities()
            );
            //no need to check the password

            //set the user as authenticated with Spring Security
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //proceed with the chain
        filterChain.doFilter(request, response);
    }

    private String getJwtFromRequest(HttpServletRequest request){
        //"Authorization": "Bearer eyJhbGciOVCJ9.eyJzdWIiOiIxJV_.adQssw5c"
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(authHeader) && StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")){
            return authHeader.substring("Bearer ".length());
        }
        return null;
    }
}
