package com.kolay.library.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookShowDto {
    private String id;
    private String author;
    private String name;
}
