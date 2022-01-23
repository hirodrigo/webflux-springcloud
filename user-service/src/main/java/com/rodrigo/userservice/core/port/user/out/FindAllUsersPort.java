package com.rodrigo.userservice.core.port.user.out;

import com.rodrigo.userservice.core.domain.User;
import reactor.core.publisher.Flux;

public interface FindAllUsersPort {

    Flux<User> findAll();
}
