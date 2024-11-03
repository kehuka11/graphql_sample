package com.example.graphqlserver.infra.book_rental.impl;

import static jooq.gen.tables.Author.AUTHOR;

import com.example.graphqlserver.domain.book_rental.model.Author;
import com.example.graphqlserver.domain.book_rental.repository.AuthorRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

  @Autowired DSLContext dslContext;

  public boolean existById(String authorId) {
    int count = dslContext.selectCount().from(AUTHOR).where(AUTHOR.AUTHORID.eq(authorId)).execute();

    return count != 0;
  }

  public void register(Author author) {
    dslContext
        .insertInto(AUTHOR)
        .set(AUTHOR.AUTHORID, author.id())
        .set(AUTHOR.FIRSTNAME, author.firstName())
        .set(AUTHOR.LASTNAME, author.lastName())
        .execute();
  }
}
