package com.ust.bookservice.domain;

public record BookDTO(
         Long id,
         String title,
         Author author) {
}
