package com.jinnara.customer.anno;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@PersonInfo(mention = "뭘바.")
public class Trash {
  private String name;
  private int age;
}
