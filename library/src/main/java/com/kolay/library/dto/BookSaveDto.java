package com.kolay.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookSaveDto {

    @NotBlank(message = "author is required")
    private String author;

    @NotBlank(message = "name is required")
    private String name;

    @NotBlank(message = "content is required")
    private String content;
}
