package com.rodrigo.userservice.adapter.database.user.repository;

import com.rodrigo.userservice.adapter.database.user.entity.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.UUID;

public interface UserRepository extends ReactiveMongoRepository<UserEntity, UUID> {
}
