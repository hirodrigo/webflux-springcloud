package com.rodrigo.userservice.adapter.web.user;

import com.rodrigo.userservice.adapter.web.user.dto.request.CreateUserRequestDto;
import com.rodrigo.userservice.adapter.web.user.dto.response.CreateUserResponseDto;
import com.rodrigo.userservice.adapter.web.user.dto.response.FindAllUsersResponseDto;
import com.rodrigo.userservice.adapter.web.user.dto.response.FindUserByIdResponseDto;
import com.rodrigo.userservice.adapter.web.user.mapper.CreateUserResponseMapper;
import com.rodrigo.userservice.adapter.web.user.mapper.FindAllUsersResponseMapper;
import com.rodrigo.userservice.adapter.web.user.mapper.FindUserByIdResponseMapper;
import com.rodrigo.userservice.core.domain.User;
import com.rodrigo.userservice.core.port.user.in.CreateUserUseCase;
import com.rodrigo.userservice.core.port.user.in.FindAllUsersUseCase;
import com.rodrigo.userservice.core.port.user.in.FindUserByIdUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private FindAllUsersUseCase findAllUsersUseCase;

    @MockBean
    private FindUserByIdUseCase findUserByIdUseCase;

    @MockBean
    private CreateUserUseCase createUserUseCase;

    @Test
    void shouldReturnFindAllUsersResponseDtoFlux() {
        User user1 = buildUser();
        user1.setName("Nome Teste 1");
        User user2 = buildUser();
        user2.setName("Nome Teste 2");

        doReturn(Flux.just(user1, user2)).when(findAllUsersUseCase).findAll();

        List<FindAllUsersResponseDto> expected = Arrays.asList(
                FindAllUsersResponseMapper.toDto(user1),
                FindAllUsersResponseMapper.toDto(user2));

        webTestClient.get().uri("/users/").exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBodyList(FindAllUsersResponseDto.class)
                .isEqualTo(expected);
    }

    @Test
    void shouldReturnFindUserByIdResponseDtoMono() {
        User user = buildUser();

        doReturn(Mono.just(user)).when(findUserByIdUseCase).findById(any(UUID.class));

        FindUserByIdResponseDto expected = FindUserByIdResponseMapper.toDto(user);

        webTestClient.get().uri("/users/" + user.getId()).exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBody(FindUserByIdResponseDto.class)
                .isEqualTo(expected);
    }

    @Test
    void shouldReturnCreateUserResponseDtoMono() {
        User user = buildUser();
        CreateUserRequestDto createUserRequestDto = buildCreateUserRequestDto();

        doReturn(Mono.just(user)).when(createUserUseCase).create(any(User.class));

        webTestClient.post().uri("/users/")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(createUserRequestDto), CreateUserRequestDto.class)
                .exchange()
                .expectStatus().isCreated()
                .expectHeader().contentType(MediaType.APPLICATION_JSON_VALUE)
                .expectBody(CreateUserResponseDto.class)
                .isEqualTo(CreateUserResponseMapper.toDto(user));
    }

    private User buildUser(){
        return User.builder()
                .id(UUID.randomUUID())
                .name("Nome Teste").build();
    }

    private CreateUserRequestDto buildCreateUserRequestDto(){
        return CreateUserRequestDto.builder()
                .name("Nome Teste")
                .build();
    }
}