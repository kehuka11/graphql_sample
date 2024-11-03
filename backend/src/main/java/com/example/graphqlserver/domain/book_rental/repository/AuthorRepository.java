package com.example.graphqlserver.domain.book_rental.repository;

import com.example.graphqlserver.domain.book_rental.model.Author;

public interface AuthorRepository {
  boolean existById(String authorId);

  void register(Author author);
}
