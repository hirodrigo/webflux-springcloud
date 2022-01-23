package com.rodrigo.userservice.adapter.database.user;

import com.rodrigo.userservice.adapter.database.user.mapper.UserEntityMapper;
import com.rodrigo.userservice.adapter.database.user.repository.UserRepository;
import com.rodrigo.userservice.core.domain.User;
import com.rodrigo.userservice.core.port.user.out.CreateUserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreateUserAdapter implements CreateUserPort {

    private final UserRepository userRepository;

    @Override
    public Mono<User> create(User user) {
        try {
            return userRepository.save(UserEntityMapper.toEntity(user))
                    .map(UserEntityMapper::toDomain);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create User.");
        }
    }
}
