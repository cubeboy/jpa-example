package com.jinnara.customer.anno;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@PersonInfo(mention = "반가워요.")
public class Person {
  private String name;
  private int age;
}
