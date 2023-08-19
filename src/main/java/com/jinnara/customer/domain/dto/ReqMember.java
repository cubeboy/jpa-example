package com.jinnara.customer.domain.dto;

import com.jinnara.customer.domain.model.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReqMember {
  @Builder.Default
  private final String name = null;
  @Builder.Default
  private final Pair<Integer, Integer> age = null;

  public Specification<Member> crateSpec() {
    Specification<Member> spec = Specification.where(null);
    if(name != null){
      spec.and((root, query, builder) -> builder.equal(root.get("name"), name));
    }
    if(age != null){
      spec.and((root, query, builder) -> builder.between(root.get("age"), age.getFirst(), age.getSecond()));
    }
    return spec;
  }
}
