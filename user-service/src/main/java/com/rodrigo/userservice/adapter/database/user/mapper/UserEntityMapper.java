package com.rodrigo.userservice.adapter.database.user.mapper;

import com.rodrigo.userservice.adapter.database.user.entity.UserEntity;
import com.rodrigo.userservice.core.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntityMapper {

    public static UserEntity toEntity(User user) {
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .build();
    }

    public static User toDomain(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .build();
    }
}
