package com.rodrigo.userservice.adapter.web.user.mapper;

import com.rodrigo.userservice.core.domain.User;
import com.rodrigo.userservice.adapter.web.user.dto.response.CreateUserResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserResponseMapper {

    public static CreateUserResponseDto toDto(User user) {
        return CreateUserResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

}
