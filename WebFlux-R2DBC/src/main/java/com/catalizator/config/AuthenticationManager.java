package com.catalizator.config;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;

    public AuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();

        String username;

        try {
            username = jwtUtil.extractUsername(authToken);
        }catch (Exception e){
            username = null;
            System.out.println(e);
        }

        if (username != null && jwtUtil.validateToken(authToken)){

        } else{
            return Mono.empty();
        }

        return null;
    }
}
