package com.example.graphqlserver.api.model.book_rental;

import com.example.graphqlserver.domain.book_rental.exception.InvalidBookException;
import com.example.graphqlserver.domain.book_rental.model.Author;

public record BookResponse(String id, String title, Author author, InvalidBookException error) {}
