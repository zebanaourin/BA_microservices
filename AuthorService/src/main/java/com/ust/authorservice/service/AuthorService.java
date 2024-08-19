package com.ust.authorservice.service;

import com.ust.authorservice.domain.Author;
import com.ust.authorservice.repository.AuthorJpaRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {

    private final AuthorJpaRepository authorRepository;

    public AuthorService(AuthorJpaRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // Create a new Author
    public Author saveAuthor(Author author) {
        // If id is not assigned then throw an exception
        if(author.getId() == null) {
            throw new IllegalArgumentException("Author ID should not be null");
        }
        // If author with the same name already exists then throw an exception
        if(authorRepository.findByName(author.getName()) != null) {
            throw new IllegalArgumentException("Author with name " +
                    author.getName() + " already exists");
        }
        System.err.println(author);
        var result =  authorRepository.save(author);
        return result;
    }

    // FInd an Author by ID
    public Author findAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    // Find all Authors
    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    // Update an Author
    public Author updateAuthor(Author author) {
        return authorRepository.save(author);
    }

    // Delete an Author
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
