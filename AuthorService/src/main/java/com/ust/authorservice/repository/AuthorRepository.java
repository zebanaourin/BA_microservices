package com.ust.authorservice.repository;


import com.ust.authorservice.domain.Author;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AuthorRepository {

    private List<Author> authors = new ArrayList<>();

    public AuthorRepository() {
        authors.add(new Author(1L, "George Orwell"));
        authors.add(new Author(2L, "J.K. Rowling"));
        authors.add(new Author(3L, "Harper Lee"));
        authors.add(new Author(4L, "J.R.R. Tolkien"));
        authors.add(new Author(5L, "Jane Austen"));
    }

    public List<Author> findAll() {
        return authors;
    }

    public Author findById(Long id) {
        return authors.stream()
                .filter(author -> author.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
