package com.rodrigo.userservice.core.service.user;

import com.rodrigo.userservice.core.domain.User;
import com.rodrigo.userservice.core.port.user.out.FindAllUsersPort;
import com.rodrigo.userservice.core.port.user.in.FindAllUsersUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Slf4j
@Component
@RequiredArgsConstructor
public class FindAllUsersService implements FindAllUsersUseCase {

    private final FindAllUsersPort findAllUsersPort;

    @Override
    public Flux<User> findAll() {
        log.info("Searching all users.");
        return findAllUsersPort.findAll();
    }

}
