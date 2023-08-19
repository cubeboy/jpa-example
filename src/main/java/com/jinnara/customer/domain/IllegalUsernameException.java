package com.jinnara.customer.domain;

public class IllegalUsernameException extends Exception {
  public IllegalUsernameException(String message) {
    super(message);
  }
}
