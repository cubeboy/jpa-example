package com.jinnara.customer.domain;

import com.jinnara.customer.common.Loggable;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
  @Loggable
  public String getMemberName(String name) throws IllegalUsernameException {
    if(name.equals("ugly")){
      throw new IllegalUsernameException(String.format("Username %s is Illegal", name));
    }
    return name;
  }
}
