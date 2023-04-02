package com.kolay.reader.client;

import com.kolay.reader.model.BookData;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "bookClient", url = "http://localhost:8090/api/books")
public interface BookClient {

    @GetMapping
    @CircuitBreaker(name = "CircuitBreakerService")
    BookData[] getBooks();

    @GetMapping(value = "/{id}")
    @CircuitBreaker(name = "CircuitBreakerService")
    BookData getBook(@PathVariable(name = "id") String id);
}
