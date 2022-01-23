package com.rodrigo.userservice.core.service.user;

import com.rodrigo.userservice.core.domain.User;
import com.rodrigo.userservice.core.port.user.out.CreateUserPort;
import com.rodrigo.userservice.core.port.user.in.CreateUserUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {

    private final CreateUserPort createUserPort;

    @Override
    public Mono<User> create(User user) {
        log.info("Creating a new user with id: " + user.getId());
        return createUserPort.create(user);
    }
}
