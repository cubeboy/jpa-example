package com.jinnara.customer.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Team {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Builder.Default
  private final Long teamId = null;

  @Builder.Default
  private final String name = null;

}
