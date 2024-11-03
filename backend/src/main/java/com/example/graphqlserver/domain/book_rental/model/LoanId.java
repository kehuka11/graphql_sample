package com.example.graphqlserver.domain.book_rental.model;

import java.util.UUID;
import java.util.regex.Pattern;
import lombok.Getter;

@Getter
public class LoanId {

  private static final String UUID_REGEX =
      "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
  private static final Pattern UUID_PATTERN = Pattern.compile(UUID_REGEX);

  /** ID */
  private UUID id;

  public LoanId(String id) {
    this.id = convertId(id);
  }

  private UUID convertId(String id) {
    if (isValidUUID(id)) {
      return UUID.fromString(id);
    } else {
      throw new RuntimeException("Invalid ID");
    }
  }

  private boolean isValidUUID(String id) {
    if (id == null) {
      return false;
    }
    return UUID_PATTERN.matcher(id).matches();
  }
}
