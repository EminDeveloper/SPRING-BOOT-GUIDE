package com.example.webfluxxab.config;

import com.example.webfluxxab.component.GreetingHandler;
import com.example.webfluxxab.domain.Greeting;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;

import java.util.Map;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration(proxyBeanMethods = false)
public class GreetingRouter {

    @Bean
    public RouterFunction<ServerResponse> route(GreetingHandler greetingHandler) {

        RequestPredicate route = GET("/hello")
                .and(accept(MediaType.APPLICATION_JSON));


        return RouterFunctions
                .route(route, greetingHandler::hello)
                .andRoute(
                        GET("/"),
                        serverRequest -> {
                            return ServerResponse
                                    .ok()
                                    .render("index", Map.of("user", ""));
//                                    .contentType(MediaType.TEXT_PLAIN)
//                                    .body(BodyInserters.fromValue("Main Page"));
                        }
                );
    }
}