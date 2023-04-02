package com.kolay.library.service;

import com.kolay.library.data.Book;
import com.kolay.library.data.UserData;
import com.kolay.library.dto.BindDto;
import com.kolay.library.dto.BookSaveDto;
import com.kolay.library.dto.BookShowDto;
import com.kolay.library.exceptions.NotFoundException;
import com.kolay.library.repository.BookRepository;
import com.kolay.library.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Book saveBook(BookSaveDto bookSaveDto) {
        Book book = Book.builder()
                .author(bookSaveDto.getAuthor())
                .name(bookSaveDto.getName())
                .content(bookSaveDto.getContent())
                .build();
        return bookRepository.save(book);
    }

    @Override
    public Book getBook(String id, UserData userData) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book with id %s not found".formatted(id)));
        List<Book> bookList = userData.getBooks();
        if (bookList == null || !bookList.contains(book)) {
            throw new RuntimeException("You don't have permission to read this book");
        }
        return book;
    }

    @Override
    public List<BookShowDto> findAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(this::toBookShowDto)
                .toList();
    }

    @Override
    public String bindBookToUser(BindDto bindDto) {
        UserData user = userRepository.findById(bindDto.getUserId())
                .orElseThrow(() -> new RuntimeException("User with id '%s' not found".formatted(bindDto.getUserId())));
        Book book = bookRepository.findById(bindDto.getBookId())
                .orElseThrow(() -> new RuntimeException("Book with id '%s' not found".formatted(bindDto.getBookId())));
        List<Book> bookList = user.getBooks();
        if (bookList == null) {
            bookList = new ArrayList<>();
        }
        bookList.add(book);
        user.setBooks(bookList);
        userRepository.save(user);
        return "Success";
    }

    @Override
    public List<BookShowDto> findBooksOfUser(UserData userFromContext) {
        List<Book> bookList = userFromContext.getBooks();
        if (bookList == null) {
            return new ArrayList<>();
        }
        return bookList.stream()
                .map(this::toBookShowDto)
                .toList();
    }

    private BookShowDto toBookShowDto(Book book) {
        return BookShowDto.builder()
                .id(book.getId())
                .author(book.getAuthor())
                .name(book.getName())
                .build();
    }
}
