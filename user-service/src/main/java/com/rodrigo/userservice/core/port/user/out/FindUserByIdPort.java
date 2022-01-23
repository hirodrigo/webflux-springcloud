package com.rodrigo.userservice.core.port.user.out;

import com.rodrigo.userservice.core.domain.User;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface FindUserByIdPort {

    Mono<User> findById(UUID id);
}
