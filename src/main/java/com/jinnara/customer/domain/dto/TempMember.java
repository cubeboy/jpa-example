package com.jinnara.customer.domain.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TempMember {
  private Long id;
  private String username;
}
