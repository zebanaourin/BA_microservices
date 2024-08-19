package com.ust.authorservice.repository;

import com.ust.authorservice.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorJpaRepository extends JpaRepository<Author, Long> {

    Author findByName(String name);
}
