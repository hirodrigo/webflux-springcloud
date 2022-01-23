package com.rodrigo.userservice.adapter.web.user;

import com.rodrigo.userservice.core.port.user.in.FindUserByIdUseCase;
import com.rodrigo.userservice.adapter.web.user.dto.request.CreateUserRequestDto;
import com.rodrigo.userservice.adapter.web.user.dto.response.CreateUserResponseDto;
import com.rodrigo.userservice.adapter.web.user.dto.response.FindAllUsersResponseDto;
import com.rodrigo.userservice.adapter.web.user.dto.response.FindUserByIdResponseDto;
import com.rodrigo.userservice.adapter.web.user.mapper.CreateUserRequestMapper;
import com.rodrigo.userservice.adapter.web.user.mapper.CreateUserResponseMapper;
import com.rodrigo.userservice.adapter.web.user.mapper.FindAllUsersResponseMapper;
import com.rodrigo.userservice.adapter.web.user.mapper.FindUserByIdResponseMapper;
import com.rodrigo.userservice.core.port.user.in.CreateUserUseCase;
import com.rodrigo.userservice.core.port.user.in.FindAllUsersUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import javax.validation.Valid;
import java.net.URI;
import java.time.Duration;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final FindAllUsersUseCase findAllUsersUseCase;
    private final FindUserByIdUseCase findUserByIdUseCase;
    private final CreateUserUseCase createUserUseCase;

    @GetMapping
    //Make the response status and headers known immediately while the body is provided asynchronously at a later point.
    public ResponseEntity<Flux<FindAllUsersResponseDto>> findAll() {
        return ResponseEntity.ok(findAllUsersUseCase.findAll()
                .map(FindAllUsersResponseMapper::toDto));
    }

    @GetMapping(value = "/{id}")
    //Provides all three - response status, headers, and body, asynchronously at a later point.
    //This allows the response status and headers to vary depending on the outcome of asynchronous request handling.
    public Mono<ResponseEntity<FindUserByIdResponseDto>> findById(@PathVariable UUID id) {
        return findUserByIdUseCase.findById(id)
                .map(FindUserByIdResponseMapper::toDto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<CreateUserResponseDto>> create(@Valid @RequestBody CreateUserRequestDto createUserRequestDto) {
        return createUserUseCase.create(CreateUserRequestMapper.toDomain(createUserRequestDto))
                .map(CreateUserResponseMapper::toDto)
                .map(dto -> ResponseEntity.created(URI.create("/users/" + dto.getId())).body(dto));
    }

    @GetMapping(value = "/event-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<Tuple2<FindAllUsersResponseDto, Long>>> findAllEventStream() {
        return ResponseEntity.ok(findAllUsersUseCase.findAll()
                .map(FindAllUsersResponseMapper::toDto)
                .zipWith(Flux.interval(Duration.ofSeconds(10))));
    }

    @GetMapping(value = "/event-stream2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<Flux<FindAllUsersResponseDto>> findAllEventStream2() {
        return ResponseEntity.ok(Flux.interval(Duration.ofSeconds(10))
                .flatMap(sequence -> (findAllUsersUseCase.findAll()
                        .map(FindAllUsersResponseMapper::toDto))));
    }

}
