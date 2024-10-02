package com.example.graphqlserver.api.contoroller;

import com.example.graphqlserver.domain.book_rental.model.Author;
import com.example.graphqlserver.domain.book_rental.model.Book;
import com.example.graphqlserver.domain.book_rental.model.BookId;
import com.example.graphqlserver.domain.book_rental.repository.BookRepository;
import com.example.graphqlserver.usecase.book_rental.BookService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {


    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @QueryMapping
    public Book books(@Argument("id") String id) {

        return bookService.findById(id);
    }

    @QueryMapping
    public List<Book> searchBooks(@Argument("id") String id, @Argument("count") int count) {

        return bookService.searchBooks(id, count);
    }

}
