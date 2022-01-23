package com.rodrigo.userservice.adapter.web.user.mapper;

import com.rodrigo.userservice.adapter.web.user.dto.request.CreateUserRequestDto;
import com.rodrigo.userservice.core.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserRequestMapper {

    public static User toDomain(CreateUserRequestDto createUserRequestDto) {
        return User.builder()
                .id(UUID.randomUUID())
                .name(createUserRequestDto.getName())
                .build();
    }

}
