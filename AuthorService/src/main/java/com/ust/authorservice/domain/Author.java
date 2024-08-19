package com.ust.authorservice.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Schema(description = "Author details", name = "Author")
@Entity
@Table(name = "authors")
public class Author {

    @Schema(description = "Author ID", example = "1")
    @Id
    @GeneratedValue
    private Long id;

    @Schema(description = "Author name", example = "John Doe")
    private String name;

    public Author(String name) {
        this.name = name;
    }
    // Getters and setters
}
