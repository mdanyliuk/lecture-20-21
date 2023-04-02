package com.kolay.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BindDto {

    @NotBlank(message = "user id is required")
    private String userId;

    @NotBlank(message = "book id is required")
    private String bookId;
}
