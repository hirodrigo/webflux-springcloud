package com.rodrigo.userservice.adapter.web.infrastructure.config;

import com.rodrigo.userservice.adapter.web.user.UserHandler;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

//@Configuration
public class RouterConfig {

    //@Bean
    public RouterFunction<ServerResponse> route(UserHandler userHandler) {
        return RouterFunctions
                .route(GET("/users")
                        .and(accept(MediaType.APPLICATION_JSON)), userHandler::findAll)
                .andRoute(GET("/users/{id}")
                        .and(accept(MediaType.APPLICATION_JSON)), userHandler::findById)
                .andRoute(POST("/users")
                        .and(accept(MediaType.APPLICATION_JSON)), userHandler::create);
    }

}
