package com.example.graphqlserver.domain.book_rental.model;

import com.example.graphqlserver.domain.book_rental.exception.InvalidBookException;

public record Book(BookId id, String title, Author author) {
  public Book {
    if (title.length() < 1) {
      throw new InvalidBookException("Invalid Book title length", "title");
    }
  }
}
