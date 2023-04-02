package com.kolay.reader.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class BookData implements Serializable {

    private String id;

    private String author;

    private String name;

    private String content;
}
