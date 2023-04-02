package com.kolay.library.repository;

import com.kolay.library.data.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserData, String> {

    Optional<UserData> findByUsername(String username);
}
