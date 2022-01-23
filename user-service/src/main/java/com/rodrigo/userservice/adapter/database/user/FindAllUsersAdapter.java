package com.rodrigo.userservice.adapter.database.user;

import com.rodrigo.userservice.adapter.database.user.mapper.UserEntityMapper;
import com.rodrigo.userservice.adapter.database.user.repository.UserRepository;
import com.rodrigo.userservice.core.domain.User;
import com.rodrigo.userservice.core.port.user.out.FindAllUsersPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
public class FindAllUsersAdapter implements FindAllUsersPort {

    private final UserRepository userRepository;

    @Override
    public Flux<User> findAll() {
        try {
            return userRepository.findAll()
                    .map(UserEntityMapper::toDomain);
        } catch (Exception e) {
            throw new RuntimeException("Failed to find Users.");
        }
    }
}
