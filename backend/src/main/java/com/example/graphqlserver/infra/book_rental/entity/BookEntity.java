package com.example.graphqlserver.infra.book_rental.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class BookEntity {
  /** ID */
  private String id;

  /** タイトル */
  private String title;

  /** 著者 */
  private AuthorEntity author;
}
