package com.example.graphqlserver.infra.book_rental.impl;

import com.example.graphqlserver.domain.book_rental.model.Book;
import com.example.graphqlserver.domain.book_rental.model.Author;
import com.example.graphqlserver.domain.book_rental.model.BookId;
import com.example.graphqlserver.domain.book_rental.repository.BookRepository;
import com.example.graphqlserver.infra.book_rental.entity.AuthorEntity;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import static jooq.gen.tables.Book.BOOK;
import static jooq.gen.tables.Author.AUTHOR;
import com.example.graphqlserver.infra.book_rental.entity.BookEntity;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @Autowired
    DSLContext dslContext;

    @Override
    public Book findBookByID(BookId id) {

        Record record =
                dslContext.select(
                                BOOK.ID,
                                BOOK.TITLE,
                                BOOK.AUTHORID,
                                AUTHOR.AUTHORID,
                        AUTHOR.FIRSTNAME,
                        AUTHOR.LASTNAME
                        ).from(BOOK)
                        .leftJoin(AUTHOR)
                        .on(BOOK.AUTHORID.eq(AUTHOR.AUTHORID))
                        .where(BOOK.ID.eq(id.getId()))
                        .fetchOne();

        // BookEntityの生成
        BookEntity bookEntity = new BookEntity();
        bookEntity.setId(record.get(BOOK.ID));
        bookEntity.setTitle(record.get(BOOK.TITLE));

        // AuthorEntityの生成
        AuthorEntity authorEntity = new AuthorEntity();
        authorEntity.setAuthorId(record.get(AUTHOR.AUTHORID));
        authorEntity.setFirstName(record.get(AUTHOR.FIRSTNAME));
        authorEntity.setLastName(record.get(AUTHOR.LASTNAME));

        // BookEntityにAuthorEntityをセット
        bookEntity.setAuthor(authorEntity);

        Author author = new Author(authorEntity.getAuthorId(), authorEntity.getFirstName(), authorEntity.getLastName());

        return new Book(new BookId(Objects.requireNonNull(bookEntity).getId()), bookEntity.getTitle(), author);

    }


    public List<Book> SearchBooks(BookId id, int count) {

        return
                dslContext.select(
                                BOOK.ID,
                                BOOK.TITLE,
                                BOOK.AUTHORID,
                                AUTHOR.AUTHORID,
                                AUTHOR.FIRSTNAME,
                                AUTHOR.LASTNAME
                        ).from(BOOK)
                        .leftJoin(AUTHOR)
                        .on(BOOK.AUTHORID.eq(AUTHOR.AUTHORID))
                        .where(BOOK.ID.gt(id.getId()))
                        .limit(count)
                        .fetch()
                        .stream()
                        .map(record -> new Book(
                                new BookId(record.get(BOOK.ID)),
                                record.get(BOOK.TITLE),
                                new Author(
                                        record.get(AUTHOR.AUTHORID),
                                        record.get(AUTHOR.FIRSTNAME),
                                        record.get(AUTHOR.LASTNAME)
                                )
                                )
                        )
                        .collect(Collectors.toList());
    }

    public String findMaxIdWithinRange(int count) {
        Record record =
                dslContext.select(
                                BOOK.ID
                        ).from(BOOK)
                        .leftJoin(AUTHOR)
                        .on(BOOK.AUTHORID.eq(AUTHOR.AUTHORID))
                        .orderBy(BOOK.ID.desc())
                        .limit(count)
                        .fetchOne();

        return Objects.requireNonNull(record).get(BOOK.ID);
    }
}
