package com.example.graphqlserver.api.contoroller;

import com.example.graphqlserver.domain.book_rental.model.LoanId;
import com.example.graphqlserver.usecase.book_rental.LoanHistoryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class LoanHistoryController {

  private LoanHistoryService loanHistoryService;

  public LoanHistoryController(LoanHistoryService loanHistoryService) {
    this.loanHistoryService = loanHistoryService;
  }

  @MutationMapping
  public String loan(@Argument("bookId") String bookId, @Argument("userId") String userId) {

    LoanId loanId = loanHistoryService.loan(bookId, userId);
    return loanId.getId().toString();
  }
}
