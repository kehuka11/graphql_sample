package com.example.graphqlserver.infra.book_rental.impl;

import static jooq.gen.tables.ReturnHistory.RETURN_HISTORY;

import com.example.graphqlserver.domain.book_rental.repository.ReturnHistoryRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ReturnHistoryRepositoryImpl implements ReturnHistoryRepository {

  @Autowired DSLContext dslContext;

  public void returnBook(String returnId, String bookId, String userId, String loanId) {
    dslContext
        .insertInto(RETURN_HISTORY)
        .set(RETURN_HISTORY.RETURN_ID, returnId)
        .set(RETURN_HISTORY.BOOK_ID, bookId)
        .set(RETURN_HISTORY.USER_ID, userId)
        .set(RETURN_HISTORY.LOAN_ID, loanId)
        .execute();
  }
}
