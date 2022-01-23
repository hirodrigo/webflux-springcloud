package com.rodrigo.userservice.core.port.user.in;

import com.rodrigo.userservice.core.domain.User;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface FindUserByIdUseCase {

    Mono<User> findById(UUID id);
}
