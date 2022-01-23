package com.rodrigo.userservice.adapter.database.user;

import com.rodrigo.userservice.adapter.database.user.mapper.UserEntityMapper;
import com.rodrigo.userservice.adapter.database.user.repository.UserRepository;
import com.rodrigo.userservice.core.domain.User;
import com.rodrigo.userservice.core.port.user.out.FindUserByIdPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class FindUserByIdAdapter implements FindUserByIdPort {

    private final UserRepository userRepository;

    @Override
    public Mono<User> findById(UUID id) {
        try {
            return userRepository.findById(id)
                    .map(UserEntityMapper::toDomain);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find User.");
        }
    }

}
