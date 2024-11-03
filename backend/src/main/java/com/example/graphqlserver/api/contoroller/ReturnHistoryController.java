package com.example.graphqlserver.api.contoroller;

import com.example.graphqlserver.usecase.book_rental.ReturnHistoryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ReturnHistoryController {

  private final ReturnHistoryService returnHistoryService;

  public ReturnHistoryController(ReturnHistoryService returnHistoryService) {
    this.returnHistoryService = returnHistoryService;
  }

  @MutationMapping
  public String returnBook(
      @Argument("bookId") String bookId,
      @Argument("userId") String userId,
      @Argument("loanId") String loanId) {

    return returnHistoryService.returnBook(bookId, userId, loanId).getId().toString();
  }
}
