package com.example.graphqlserver.domain.book_rental.model;

public record LoanHistory(LoanId loanId, BookId bookId, String userId) {}
