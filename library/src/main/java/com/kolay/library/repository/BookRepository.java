package com.kolay.library.repository;

import com.kolay.library.data.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<Book, String> {
}
