package com.rodrigo.userservice.core.port.user.in;

import com.rodrigo.userservice.core.domain.User;
import reactor.core.publisher.Mono;

public interface CreateUserUseCase {

    Mono<User> create(User user);
}
