package com.rodrigo.userservice.core.service.user;

import com.rodrigo.userservice.core.domain.User;
import com.rodrigo.userservice.core.port.user.out.CreateUserPort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;

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
        doReturn(Mono.just(new User()))
                .when(createUserPort).create(any(User.class));

        Mono<User> userMono = createUserService.create(new User());

        Assertions.assertNotNull(userMono);
    }
}