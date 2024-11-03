package com.example.graphqlserver.exception.handler;

import graphql.ErrorType;
import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class GlobalExceptionHandler {

  @GraphQlExceptionHandler
  public GraphQLError handleCustomException(Exception e) {
    return GraphQLError.newError()
        .errorType(ErrorType.DataFetchingException)
        .message("test error")
        .build();
  }
}
