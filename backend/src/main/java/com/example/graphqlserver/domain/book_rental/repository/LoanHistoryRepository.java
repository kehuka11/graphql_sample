package com.example.graphqlserver.domain.book_rental.repository;

public interface LoanHistoryRepository {

  void loan(String loanId, String userId, String bookId);
}
