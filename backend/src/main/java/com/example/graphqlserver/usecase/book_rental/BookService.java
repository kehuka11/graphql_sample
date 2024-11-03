package com.example.graphqlserver.usecase.book_rental;

import com.example.graphqlserver.api.model.book_rental.BookResponse;
import com.example.graphqlserver.api.model.book_rental.RegisterBookV2Request;
import com.example.graphqlserver.domain.book_rental.exception.InvalidBookException;
import com.example.graphqlserver.domain.book_rental.model.Author;
import com.example.graphqlserver.domain.book_rental.model.Book;
import com.example.graphqlserver.domain.book_rental.model.BookId;
import com.example.graphqlserver.domain.book_rental.repository.AuthorRepository;
import com.example.graphqlserver.domain.book_rental.repository.BookRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private final BookRepository bookRepository;

  private final AuthorRepository authorRepository;

  public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
  }

  public Book searchById(String id) {
    BookId bookId = new BookId(id);
    return bookRepository.searchByID(bookId);
  }

  public List<Book> searchBooks(String id, int count) {

    return bookRepository.SearchBooks(id != null ? new BookId(id) : null, count);
  }

  public BookResponse registerBook(String title, Author author) {

    Book book = null;
    try {
      UUID uuid = UUID.randomUUID();
      book = new Book(new BookId(uuid.toString()), title, author);
    } catch (InvalidBookException e) {
      return new BookResponse("error-id", title, author, e);
    }

    if (title.equals("a")) {
      throw new RuntimeException("a");
    }

    if (!authorRepository.existById(author.id())) {
      authorRepository.register(author);
    }

    bookRepository.registerBook(book);

    return new BookResponse(book.id().getId().toString(), book.title(), book.author(), null);
  }

  public int registerBookV2(List<RegisterBookV2Request> bookV2Requests) {

    /*
     業務でループで1件ずつ登録しないでください。
     技術記事でサンプル作成するために、時短で既存コード流用しているだけです。
    */

    for (RegisterBookV2Request bookV2Request : bookV2Requests) {
      UUID uuid = UUID.randomUUID();
      Book book =
          new Book(new BookId(uuid.toString()), bookV2Request.title(), bookV2Request.author());

      if (!authorRepository.existById(book.author().id())) {
        authorRepository.register(book.author());
      }

      bookRepository.registerBook(book);
    }

    return bookV2Requests.size();
  }
}
