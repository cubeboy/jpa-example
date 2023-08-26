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
  @Column(name="group_id")
  String groupId;
  @Id
  @Column(name="code_id")
  String codeId;
  @Column(name="code_name")
  String codeName;
}
