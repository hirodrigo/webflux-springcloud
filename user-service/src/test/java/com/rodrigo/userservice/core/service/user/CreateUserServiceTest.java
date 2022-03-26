package com.rodrigo.userservice.core.service.user;

import com.rodrigo.userservice.core.domain.User;
import com.rodrigo.userservice.core.port.user.out.CreateUserPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class CreateUserServiceTest {

    @Mock
    private CreateUserPort createUserPort;

    @InjectMocks
    private CreateUserService createUserService;

    @Test
    void shouldReturnUserMono() {
        User user = User.builder()
                .id(UUID.randomUUID())
                .name("User Name").build();

        doReturn(Mono.just(user))
                .when(createUserPort).create(any(User.class));

        Mono<User> userMono = createUserService.create(user);

        StepVerifier.create(userMono)
                .consumeNextWith(u -> {
                    assertEquals(user.getId(), u.getId());
                    assertEquals(user.getName(), u.getName());
                }).verifyComplete();
    }
}