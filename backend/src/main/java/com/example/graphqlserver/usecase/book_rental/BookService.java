package com.example.graphqlserver.usecase.book_rental;

import com.example.graphqlserver.domain.book_rental.model.Book;
import com.example.graphqlserver.domain.book_rental.model.BookId;
import com.example.graphqlserver.domain.book_rental.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book findById(String id) {
        BookId bookId = new BookId(id);
        return bookRepository.findBookByID(bookId);
    }

    public List<Book> searchBooks(String id, int count) {
        if (id == null) {
            int searchCount = 1;
            id = bookRepository.findMaxIdWithinRange(searchCount);
        }

       return bookRepository.SearchBooks(new BookId(id), count);
    }
}
