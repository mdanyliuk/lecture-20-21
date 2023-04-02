package com.kolay.library.repository;

import com.kolay.library.data.Role;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class RoleRepository {

    private static final Map<String, Role> ROLES = List.of(
                    Role.builder()
                            .id("ROLE_USER")
                            .privileges(List.of("BOOK_ACCESS"))
                            .build(),
                    Role.builder()
                            .id("ROLE_ADMIN")
                            .privileges(List.of("USER_MANAGEMENT", "BOOK_MANAGEMENT", "BOOK_ACCESS"))
                            .build(),
                    Role.builder()
                            .id("ROLE_API_USER")
                            .privileges(List.of("API_ACCESS"))
                            .build()
            )
            .stream()
            .collect(Collectors.toUnmodifiableMap(
                    Role::getId,
                    Function.identity())
            );

    public Optional<Role> getRole(String id) {
        return Optional.ofNullable(ROLES.get(id));
    }

}
