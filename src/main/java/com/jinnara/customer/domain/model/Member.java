package com.jinnara.customer.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

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
  private Integer age;

  @ManyToOne
  @JoinColumn(name = "team_id")
  private Team team;

  @OneToOne
  @JoinColumn(name = "member_grade", referencedColumnName = "code_id", insertable = false, updatable = false)
  @Where(clause = "group_id = 'GRADE")
  private ComCode grade;
  @Column(name = "member_grade")
  private String gradeCode;

  @OneToOne
  @JoinColumn(name = "member_level", referencedColumnName = "code_id", insertable = false, updatable = false)
  @Where(clause = "group_id = 'LEVEL")
  private ComCode level;
  @Column(name = "member_level")
  private String levelCode;


}
