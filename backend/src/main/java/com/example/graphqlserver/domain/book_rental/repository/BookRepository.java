package com.example.graphqlserver.domain.book_rental.repository;

import com.example.graphqlserver.domain.book_rental.model.Book;
import com.example.graphqlserver.domain.book_rental.model.BookId;
import java.util.List;

public interface BookRepository {
  Book searchByID(BookId id);

  List<Book> SearchBooks(BookId id, int count);

  String findMaxIdWithinRange(int count);

  String findMinId();

  void registerBook(Book book);
}
