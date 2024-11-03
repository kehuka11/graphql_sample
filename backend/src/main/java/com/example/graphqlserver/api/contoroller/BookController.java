package com.example.graphqlserver.api.contoroller;

import com.example.graphqlserver.api.model.book_rental.BookResponse;
import com.example.graphqlserver.api.model.book_rental.RegisterBookV2Request;
import com.example.graphqlserver.domain.book_rental.model.Author;
import com.example.graphqlserver.domain.book_rental.model.Book;
import com.example.graphqlserver.usecase.book_rental.BookService;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class BookController {

  private final BookService bookService;

  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @QueryMapping(name = "searchBookById")
  public BookResponse searchById(@Argument("id") String id) {

    Book book = bookService.searchById(id);

    return new BookResponse(book.id().getId().toString(), book.title(), book.author(), null);
  }

  @QueryMapping
  public List<BookResponse> searchBooks(@Argument("id") String id, @Argument("count") int count) {

    return bookService.searchBooks(id, count).stream()
        .map(
            item ->
                new BookResponse(item.id().getId().toString(), item.title(), item.author(), null))
        .collect(Collectors.toList());
  }

  @MutationMapping
  public BookResponse registerBook(
      @Argument("title") String title, @Argument("author") Author author) {

    return bookService.registerBook(title, author);
  }

  @MutationMapping
  public int registerBookV2(@Argument("bookList") List<RegisterBookV2Request> bookV2Request) {

    return bookService.registerBookV2(bookV2Request);
  }
}
