package com.jinnara.customer.infra.repository;

import com.jinnara.customer.domain.dto.ReqMember;
import com.jinnara.customer.domain.model.Member;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.util.Pair;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class AutoSpecRepositoryTests {
  @Autowired
  SpecMemberRepository repository;

  @Test
  @Sql("/insertMember.sql")
  public void makeAutoSpecEqualAndTest() {
    ReqMember reqParam = ReqMember.builder()
        //.age(Pair.of(11, 15))
        .name("user111")
        .age(11)
        .build();

    Sort sort = Sort.by(
        Sort.Order.asc("memberId"),
        Sort.Order.desc("name")
    );
    Pageable pageable = PageRequest.of(0, 5, sort);

    Specification<Member> spec = reqParam.toSpec();

    Page<Member> members = repository.findAll(spec, pageable);

    assertEquals(1, members.getTotalPages());
    assertEquals(1, members.getTotalElements());

    assertEquals("user111", members.getContent().get(0).getName());
    assertEquals(11, members.getContent().get(0).getAge());
  }
}
