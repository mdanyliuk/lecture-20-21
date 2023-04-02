package com.kolay.library.data;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("users")
public class UserData {

    @Id
    private String id;

    private String username;

    private String password;

    private String role;

    private boolean enabled;

    @DBRef
    private List<Book> books;
}
