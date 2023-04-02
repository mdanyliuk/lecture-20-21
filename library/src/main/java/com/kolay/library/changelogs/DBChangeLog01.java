package com.kolay.library.changelogs;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.kolay.library.data.Book;
import com.kolay.library.data.UserData;
import com.kolay.library.repository.BookRepository;
import com.kolay.library.repository.UserRepository;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;

@ChangeLog
public class DBChangeLog01 {

    private static final String LOREM_IPSUM = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sed arcu felis. Duis eleifend nunc et semper dapibus. Etiam egestas lectus a ultrices blandit. Suspendisse vitae feugiat arcu. Quisque tempor nulla ante, a convallis ligula vulputate quis. Vivamus ultricies mi velit, vitae hendrerit ex eleifend a. Nam quis sapien dictum, hendrerit eros non, volutpat justo. Morbi molestie, enim sed rutrum fermentum, quam massa dignissim dui, nec euismod mi nisi in erat. Ut a leo a nunc auctor dignissim. Nam ut est feugiat, aliquet dui in, mollis metus. Mauris blandit, ligula at placerat vehicula, diam dui efficitur risus, non gravida nibh leo vel ex. Nulla turpis ex, tincidunt vulputate porttitor sed, tempor vel ante. Cras fringilla lorem ut neque gravida, vel interdum magna pharetra. ";

    @ChangeSet(order = "001", id = "seedDatabase", author = "mongock")
    public void seedUsers(UserRepository userRepository, BookRepository bookRepository) {

        Book book0 = Book.builder()
                .author("William Shakespeare")
                .name("Hamlet")
                .content(LOREM_IPSUM)
                .build();
        Book book1 = Book.builder()
                .author("William Shakespeare")
                .name("Macbeth")
                .content(LOREM_IPSUM)
                .build();
        Book book2 = Book.builder()
                .author("Charles Dickens")
                .name("Oliver Twist")
                .content(LOREM_IPSUM)
                .build();
        Book book3 = Book.builder()
                .author("Lewis Carroll")
                .name("Alice's Adventures in Wonderland")
                .content(LOREM_IPSUM)
                .build();
        Book book4 = Book.builder()
                .author("Oscar Wilde")
                .name("The Picture of Dorian Gray")
                .content(LOREM_IPSUM)
                .build();

        List<Book> books = Arrays.asList(book0, book1, book2, book3, book4);
        bookRepository.saveAll(books);

        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        UserData admin = UserData.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                .role("ROLE_ADMIN")
                .enabled(true)
                .books(List.of(book0, book2, book4))
                .build();
        UserData user = UserData.builder()
                .username("user")
                .password(passwordEncoder.encode("user"))
                .role("ROLE_USER")
                .books(List.of(book1, book3))
                .enabled(true)
                .build();

        List<UserData> users = Arrays.asList(admin, user);
        userRepository.saveAll(users);

    }


}
