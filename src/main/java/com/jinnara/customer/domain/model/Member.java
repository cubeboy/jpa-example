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
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Builder.Default
  private final Long memberId = null;
  @Builder.Default
  private final String name = "";
  private String city;
  private String street;
  private String zipCode;

  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;
}
