package com.kolay.library.service;

import com.kolay.library.data.Book;
import com.kolay.library.data.UserData;
import com.kolay.library.dto.BindDto;
import com.kolay.library.dto.BookSaveDto;
import com.kolay.library.dto.BookShowDto;

import java.util.List;

public interface BookService {

    Book saveBook(BookSaveDto bookSaveDto);
    Book getBook(String id, UserData userData);

    List<BookShowDto> findAllBooks();

    String bindBookToUser(BindDto bindDto);

    List<BookShowDto> findBooksOfUser(UserData userFromContext);
}
