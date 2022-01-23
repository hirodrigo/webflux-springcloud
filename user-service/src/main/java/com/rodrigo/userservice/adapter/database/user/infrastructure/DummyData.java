package com.rodrigo.userservice.adapter.database.user.infrastructure;

import com.rodrigo.userservice.adapter.database.user.repository.UserRepository;
import com.rodrigo.userservice.adapter.database.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class DummyData implements CommandLineRunner {

    private final UserRepository userRepository;

    @Override
    public void run(String... args) {
        userRepository.deleteAll()
                .thenMany(
                        Flux.just(
                                "Usuário 1",
                                "Usuário 2",
                                "Usuário 3"
                        ).map(name -> UserEntity.builder()
                                .id(UUID.randomUUID())
                                .name(name)
                                .build()
                        ).flatMap(userRepository::save)
                ).subscribe();
    }
}
