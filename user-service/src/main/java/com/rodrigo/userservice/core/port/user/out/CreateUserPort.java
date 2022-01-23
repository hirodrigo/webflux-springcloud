package com.rodrigo.userservice.core.port.user.out;

import com.rodrigo.userservice.core.domain.User;
import reactor.core.publisher.Mono;

public interface CreateUserPort {

    Mono<User> create(User user);
}
