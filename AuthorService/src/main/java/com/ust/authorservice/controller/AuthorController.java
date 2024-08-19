package com.ust.authorservice.controller;


import com.ust.authorservice.domain.Author;
import com.ust.authorservice.repository.AuthorRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {

    @Autowired
    private AuthorRepository authorRepository;

    @GetMapping("/authors")
    @Operation(summary = "Get all authors", description = "Get all authors from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authors returned successfully"),
            @ApiResponse(responseCode = "404", description = "Authors not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/authors/{id}")
    @Operation(summary = "Get author by ID", description = "Get author by ID from the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author returned successfully"),
            @ApiResponse(responseCode = "404", description = "Author not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public Author getAuthorById(@PathVariable @Parameter(name = "id", description = "ID of the author") Long id) {
        return authorRepository.findById(id);
    }
}
