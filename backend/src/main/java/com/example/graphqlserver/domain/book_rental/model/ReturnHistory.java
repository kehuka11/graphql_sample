package com.example.graphqlserver.domain.book_rental.model;

public record ReturnHistory(ReturnId returnId, BookId bookId, String userId, LoanId loanId) {}
