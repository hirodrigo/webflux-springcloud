package com.rodrigo.userservice.adapter.web.user.mapper;

import com.rodrigo.userservice.core.domain.User;
import com.rodrigo.userservice.adapter.web.user.dto.response.FindUserByIdResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindUserByIdResponseMapper {

    public static FindUserByIdResponseDto toDto(User user){
        return FindUserByIdResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

}
