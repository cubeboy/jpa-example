package com.jinnara.customer.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "member")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SmallMember {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Builder.Default
  private final Long memberId = null;
  @Builder.Default
  private final String name = "";
  @Builder.Default
  private final Integer age = 0;
}
