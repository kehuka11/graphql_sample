package com.example.graphqlserver.usecase.book_rental;

import com.example.graphqlserver.domain.book_rental.model.BookId;
import com.example.graphqlserver.domain.book_rental.model.LoanHistory;
import com.example.graphqlserver.domain.book_rental.model.LoanId;
import com.example.graphqlserver.domain.book_rental.repository.LoanHistoryRepository;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class LoanHistoryService {

  private final LoanHistoryRepository loanHistoryRepository;

  public LoanHistoryService(LoanHistoryRepository loanHistoryRepository) {
    this.loanHistoryRepository = loanHistoryRepository;
  }

  public LoanId loan(String bookId, String userId) {
    UUID uuid = UUID.randomUUID();

    LoanHistory loanHistory =
        new LoanHistory(new LoanId(uuid.toString()), new BookId(bookId), userId);

    try {
      loanHistoryRepository.loan(
          loanHistory.loanId().getId().toString(),
          loanHistory.bookId().getId().toString(),
          loanHistory.userId());
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }

    return loanHistory.loanId();
  }
}
