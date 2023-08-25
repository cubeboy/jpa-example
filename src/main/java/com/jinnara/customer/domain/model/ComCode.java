package com.jinnara.customer.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@IdClass(ComCodePk.class)
@Entity
@Table(name = "com_code")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ComCode {
  @Id
  @Column
  String groupId;
  @Id
  @Column
  String codeId;
  @Column
  String codeName;
}
