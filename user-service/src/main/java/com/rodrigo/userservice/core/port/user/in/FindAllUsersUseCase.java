package com.rodrigo.userservice.core.port.user.in;

import com.rodrigo.userservice.core.domain.User;
import reactor.core.publisher.Flux;

public interface FindAllUsersUseCase {

    Flux<User> findAll();
}
