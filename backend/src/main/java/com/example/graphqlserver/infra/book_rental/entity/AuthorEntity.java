package com.example.graphqlserver.infra.book_rental.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class AuthorEntity {
    private String authorId;
    private String firstName;
    private String lastName;
}
