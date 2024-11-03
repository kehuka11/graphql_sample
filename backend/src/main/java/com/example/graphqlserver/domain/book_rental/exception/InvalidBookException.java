package com.example.graphqlserver.domain.book_rental.exception;

public class InvalidBookException extends BookRentalException {

  private final String field;

  public InvalidBookException(String message, String field) {
    super(message);

    this.field = field;
  }
}
