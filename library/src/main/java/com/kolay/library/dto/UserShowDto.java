package com.kolay.library.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserShowDto {

    private String id;
    private String username;

}
