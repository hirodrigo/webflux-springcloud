package com.rodrigo.userservice.core.service.user;

import com.rodrigo.userservice.core.domain.User;
import com.rodrigo.userservice.core.port.user.in.FindUserByIdUseCase;
import com.rodrigo.userservice.core.port.user.out.FindUserByIdPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindUserByIdService implements FindUserByIdUseCase {

    private final FindUserByIdPort findUserByIdPort;

    @Override
    public Mono<User> findById(UUID id) {
        log.info("Searching an user by id: " + id);
        return findUserByIdPort.findById(id);
    }
}
