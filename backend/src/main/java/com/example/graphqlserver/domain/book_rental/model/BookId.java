package com.example.graphqlserver.domain.book_rental.model;

import lombok.Getter;

@Getter
public class BookId {
    /** ID */
    private String id;

    public BookId(String id) {
        this.id = id;
    }
}
