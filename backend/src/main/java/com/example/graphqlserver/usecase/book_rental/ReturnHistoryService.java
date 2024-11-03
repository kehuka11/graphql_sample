package com.example.graphqlserver.usecase.book_rental;

import com.example.graphqlserver.domain.book_rental.model.BookId;
import com.example.graphqlserver.domain.book_rental.model.LoanId;
import com.example.graphqlserver.domain.book_rental.model.ReturnHistory;
import com.example.graphqlserver.domain.book_rental.model.ReturnId;
import com.example.graphqlserver.domain.book_rental.repository.ReturnHistoryRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class ReturnHistoryService {

  private final ReturnHistoryRepository returnHistoryRepository;

  public ReturnHistoryService(ReturnHistoryRepository returnHistoryRepository) {
    this.returnHistoryRepository = returnHistoryRepository;
  }

  public ReturnId returnBook(String bookId, String userId, String loanId) {
    UUID uuid = UUID.randomUUID();

    ReturnHistory returnHistory =
        new ReturnHistory(
            new ReturnId(uuid.toString()), new BookId(bookId), userId, new LoanId(loanId));

    returnHistoryRepository.returnBook(
        returnHistory.returnId().getId().toString(),
        returnHistory.bookId().getId().toString(),
        returnHistory.userId(),
        returnHistory.loanId().getId().toString());

    return returnHistory.returnId();
  }
}
