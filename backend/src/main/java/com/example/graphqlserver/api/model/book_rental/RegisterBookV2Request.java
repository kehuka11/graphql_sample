package com.example.graphqlserver.api.model.book_rental;

import com.example.graphqlserver.domain.book_rental.model.Author;

public record RegisterBookV2Request(String title, Author author) {}
