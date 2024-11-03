package com.example.graphqlserver.infra.book_rental.impl;

import static jooq.gen.tables.LoanHistory.LOAN_HISTORY;

import com.example.graphqlserver.domain.book_rental.repository.LoanHistoryRepository;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoanHistoryRepositoryImpl implements LoanHistoryRepository {

  @Autowired DSLContext dslContext;

  public void loan(String loanId, String bookId, String userId) {
    dslContext
        .insertInto(LOAN_HISTORY)
        .set(LOAN_HISTORY.LOAN_ID, loanId)
        .set(LOAN_HISTORY.BOOK_ID, bookId)
        .set(LOAN_HISTORY.USER_ID, userId)
        .execute();
  }
}
