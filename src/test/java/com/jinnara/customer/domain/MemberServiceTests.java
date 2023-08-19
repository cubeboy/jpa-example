package com.jinnara.customer.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MemberServiceTests {
  @Autowired
  MemberService service;

  @Test
  public void test() throws Exception {
    String name = "UserName";
    String ret = service.getMemberName(name);
    assertEquals(name, ret);
  }

  @Test
  public void testException() {
    String name = "ugly";
    assertThrows(IllegalUsernameException.class, () -> {
      service.getMemberName(name);
    });
  }
}
