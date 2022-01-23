package com.rodrigo.userservice.adapter.web.user;

import com.rodrigo.userservice.core.port.user.in.FindUserByIdUseCase;
import com.rodrigo.userservice.adapter.web.user.dto.request.CreateUserRequestDto;
import com.rodrigo.userservice.adapter.web.user.dto.response.FindAllUsersResponseDto;
import com.rodrigo.userservice.adapter.web.user.mapper.CreateUserRequestMapper;
import com.rodrigo.userservice.adapter.web.user.mapper.CreateUserResponseMapper;
import com.rodrigo.userservice.adapter.web.user.mapper.FindAllUsersResponseMapper;
import com.rodrigo.userservice.adapter.web.user.mapper.FindUserByIdResponseMapper;
import com.rodrigo.userservice.core.port.user.in.CreateUserUseCase;
import com.rodrigo.userservice.core.port.user.in.FindAllUsersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.UUID;

import static org.springframework.web.reactive.function.BodyInserters.fromValue;

//@Component
@RequiredArgsConstructor
public class UserHandler {

    private final FindAllUsersUseCase findAllUsersUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final CreateUserUseCase createUserUseCase;

    public Mono<ServerResponse> findAll(ServerRequest serverRequest) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(findAllUsersUseCase.findAll()
                        .map(FindAllUsersResponseMapper::toDto), FindAllUsersResponseDto.class);
    }

    public Mono<ServerResponse> findById(ServerRequest serverRequest) {
        final UUID id = UUID.fromString(serverRequest.pathVariable("id"));
        return findUserByIdUseCase.findById(id).flatMap(
                user -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(fromValue(FindUserByIdResponseMapper.toDto(user)))
        );
    }

    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(CreateUserRequestDto.class)
                .map(CreateUserRequestMapper::toDomain)
                .flatMap(createUserUseCase::create)
                .flatMap(user ->
                        ServerResponse.created(URI.create("/users/" + user.getId()))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(fromValue(CreateUserResponseMapper.toDto(user))));
    }

}
