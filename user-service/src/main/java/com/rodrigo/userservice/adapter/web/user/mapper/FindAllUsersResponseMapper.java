package com.rodrigo.userservice.adapter.web.user.mapper;

import com.rodrigo.userservice.core.domain.User;
import com.rodrigo.userservice.adapter.web.user.dto.response.FindAllUsersResponseDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FindAllUsersResponseMapper {

    public static FindAllUsersResponseDto toDto(User user){
        return FindAllUsersResponseDto.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

}
