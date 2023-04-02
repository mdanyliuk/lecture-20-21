package com.kolay.library.controller;

import com.kolay.library.data.Book;
import com.kolay.library.data.UserData;
import com.kolay.library.dto.BindDto;
import com.kolay.library.dto.BookSaveDto;
import com.kolay.library.dto.BookShowDto;
import com.kolay.library.service.BookService;
import com.kolay.library.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    @Autowired
    BookService bookService;
    @Autowired
    UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Book newBook(@RequestBody @Valid BookSaveDto newBook) {
        return bookService.saveBook(newBook);
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable String id) {
        return bookService.getBook(id, getUserFromContext());
    }

    @GetMapping
    public List<BookShowDto> findBooksOfUser() {
        return bookService.findBooksOfUser(getUserFromContext());
    }

    @GetMapping("/all")
    public List<BookShowDto> findAllBooks() {
        return bookService.findAllBooks();
    }

    @PostMapping("/bind")
    public String bindBookToUser(@RequestBody @Valid BindDto bindDto) {
        return bookService.bindBookToUser(bindDto);
    }

    private UserData getUserFromContext() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof User user) {
            return userService.getUserDataByUsername(user.getUsername());
        }
        throw new RuntimeException("Unknown user");
    }
}
