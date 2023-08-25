package com.jinnara.customer.anno;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonInfoTests {
  Logger logger = LoggerFactory.getLogger(this.getClass());

  @Test
  public void helloTest() {
    Person p = Person.builder().name("kim").age(9).build();
    List<PersonInfo> personInfos = Arrays.stream(p.getClass().getDeclaredAnnotations())
        .filter(item -> item instanceof PersonInfo)
        .map(item -> (PersonInfo)item).toList();

    logger.info("PersonInfo.mention ==> {}", personInfos.get(0).mention());
    assertEquals("반가워요.", personInfos.get(0).mention());
  }
}
