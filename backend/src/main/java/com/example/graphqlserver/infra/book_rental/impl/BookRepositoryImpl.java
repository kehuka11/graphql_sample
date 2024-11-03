package com.example.graphqlserver.infra.book_rental.impl;

import static jooq.gen.tables.Author.AUTHOR;
import static jooq.gen.tables.Book.BOOK;

import com.example.graphqlserver.domain.book_rental.model.Author;
import com.example.graphqlserver.domain.book_rental.model.Book;
import com.example.graphqlserver.domain.book_rental.model.BookId;
import com.example.graphqlserver.domain.book_rental.repository.BookRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.impl.DSL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {
  @Autowired DSLContext dslContext;

  @Override
  public Book searchByID(BookId id) {

    Record record =
        dslContext
            .select(
                BOOK.ID,
                BOOK.TITLE,
                BOOK.AUTHORID,
                AUTHOR.AUTHORID,
                AUTHOR.FIRSTNAME,
                AUTHOR.LASTNAME)
            .from(BOOK)
            .leftJoin(AUTHOR)
            .on(BOOK.AUTHORID.eq(AUTHOR.AUTHORID))
            .where(BOOK.ID.eq(id.getId().toString()))
            .fetchOne();

    return new Book(
        new BookId(Objects.requireNonNull(record).get(BOOK.ID)),
        record.get(BOOK.TITLE),
        new Author(
            record.get(AUTHOR.AUTHORID),
            record.get(AUTHOR.FIRSTNAME),
            record.get(AUTHOR.LASTNAME)));
  }

  @Override
  public List<Book> SearchBooks(BookId id, int count) {

    // 動的に条件を組み立てる
    Condition condition = DSL.trueCondition();

    if (id != null) {
      condition = condition.and(BOOK.ID.gt(id.getId().toString()));
    }

    return dslContext
        .select(
            BOOK.ID, BOOK.TITLE, BOOK.AUTHORID, AUTHOR.AUTHORID, AUTHOR.FIRSTNAME, AUTHOR.LASTNAME)
        .from(BOOK)
        .leftJoin(AUTHOR)
        .on(BOOK.AUTHORID.eq(AUTHOR.AUTHORID))
        .where(condition)
        .orderBy(BOOK.ID)
        .limit(count)
        .fetch()
        .stream()
        .map(
            record ->
                new Book(
                    new BookId(record.get(BOOK.ID)),
                    record.get(BOOK.TITLE),
                    new Author(
                        record.get(AUTHOR.AUTHORID),
                        record.get(AUTHOR.FIRSTNAME),
                        record.get(AUTHOR.LASTNAME))))
        .collect(Collectors.toList());
  }

  public String findMaxIdWithinRange(int count) {
    Record record =
        dslContext
            .select(BOOK.ID)
            .from(BOOK)
            .leftJoin(AUTHOR)
            .on(BOOK.AUTHORID.eq(AUTHOR.AUTHORID))
            .orderBy(BOOK.ID.desc())
            .limit(count)
            .fetchOne();

    return Objects.requireNonNull(record).get(BOOK.ID);
  }

  public String findMinId() {
    Record record =
        dslContext
            .select(BOOK.ID)
            .from(BOOK)
            .leftJoin(AUTHOR)
            .on(BOOK.AUTHORID.eq(AUTHOR.AUTHORID))
            .orderBy(BOOK.ID)
            .fetchOne();

    return Objects.requireNonNull(record).get(BOOK.ID);
  }

  @Override
  public void registerBook(Book book) {
    dslContext
        .insertInto(BOOK)
        .set(BOOK.ID, book.id().getId().toString())
        .set(BOOK.TITLE, book.title())
        .set(BOOK.AUTHORID, book.author().id())
        .execute();
  }
}
