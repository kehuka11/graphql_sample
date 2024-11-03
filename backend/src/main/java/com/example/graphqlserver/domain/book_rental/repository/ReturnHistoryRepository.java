package com.example.graphqlserver.domain.book_rental.repository;

public interface ReturnHistoryRepository {

  void returnBook(String returnId, String bookId, String userId, String loanId);
}
