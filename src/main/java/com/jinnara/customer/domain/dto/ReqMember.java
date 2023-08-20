package com.jinnara.customer.domain.dto;

import com.jinnara.customer.common.ColumnEquals;
import com.jinnara.customer.domain.model.Member;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.data.util.Predicates;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class ReqMember {
  @Builder.Default
  @ColumnEquals("name")
  private final String name = null;
  @Builder.Default
  @ColumnEquals("age")
  private Integer age = null;
  //private final Pair<Integer, Integer> age = null;

  public Specification<Member> toSpec() {
    Specification<Member> spec = ((root, query, builder) -> {
      List<Predicate> predicatesList = Arrays.stream(this.getClass().getDeclaredFields())
          .filter(field -> field.isAnnotationPresent(ColumnEquals.class))
          .peek(field -> field.setAccessible(true))
          .map(field -> {
            try {
              Object value = field.get(this);
              return builder.equal(root.get(field.getName()), value);
            } catch (IllegalAccessException e) {
              e.printStackTrace();
              return null;
            }
          })
          .filter(p -> p != null)
          .collect(Collectors.toList());
      return builder.and(predicatesList.toArray(new Predicate[0]));
    });
    return spec;
  }
}
