package com.ust.bookservice.controller;


import com.ust.bookservice.clients.AuthorClient;
import com.ust.bookservice.domain.Book;
import com.ust.bookservice.domain.BookDTO;
import com.ust.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorClient authorClient;

    @GetMapping("/books")
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(book -> {
                    var author = authorClient.getAuthorById(book.getAuthorId());
                    return new BookDTO(book.getId(), book.getTitle(), authorClient.getAuthorById(book.getAuthorId()));
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/books/{id}")
    public BookDTO getBookById(@PathVariable Long id) {
        Book book = bookRepository.findById(id);
        var author = authorClient.getAuthorById(book.getAuthorId());
        return new BookDTO(book.getId(), book.getTitle(), author);
    }
}
