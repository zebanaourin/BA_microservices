package com.ust.bookservice.clients;

import com.ust.bookservice.domain.Author;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "AuthorService")
public interface AuthorClient {

    @GetMapping("/authors/{id}")
    Author getAuthorById(@PathVariable Long id);
}
