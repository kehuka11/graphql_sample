package com.example.graphqlserver.domain.book_rental.model;

import lombok.Data;

@Data
public class Book {
    /** ID */
    private BookId id;

    /** タイトル */
    private String title;

    /** 著者 */
    private Author author;

    public Book(BookId id, String title, Author author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

}
