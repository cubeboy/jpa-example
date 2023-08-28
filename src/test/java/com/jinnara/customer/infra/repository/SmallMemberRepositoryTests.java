package com.jinnara.customer.infra.repository;

import com.jinnara.customer.domain.model.Member;
import com.jinnara.customer.domain.model.SmallMember;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class SmallMemberRepositoryTests {
  Logger logger = LoggerFactory.getLogger(SmallMemberRepositoryTests.class);
  @Autowired
  SmallMemberRepository repository;

  @Autowired
  MemberRepository memberRepository;

  @Test
  public void insertAndSelect() {
    SmallMember member = SmallMember.builder()
        .name("smallMember")
        .build();

    SmallMember savedMember = repository.saveAndFlush(member);
    assertTrue(savedMember.getMemberId() > 0);
    assertEquals("smallMember", savedMember.getName());
    logger.info("savedMember.getMemberId() ==>> {}", savedMember.getMemberId());

    SmallMember smallMember = repository.findById(savedMember.getMemberId()).orElse(null);
    assertNotNull(smallMember);
    assertEquals(savedMember.getMemberId(), smallMember.getMemberId());
    assertEquals("smallMember", smallMember.getName());

    logger.info("tr test ================");

    Member bigMember = memberRepository.findById(savedMember.getMemberId()).orElse(null);
    assertNotNull(bigMember);
    assertEquals(savedMember.getMemberId(), bigMember.getMemberId());
    assertEquals("smallMember", bigMember.getName());
    assertNull(bigMember.getTeam());
  }

  @Test
  @Sql("/insertMember.sql")
  public void selectOldMember() {
    SmallMember member = repository.findById(118L).orElse(null);
    assertNotNull(member);
    assertEquals(118L, member.getMemberId());
    assertEquals("user118", member.getName());
  }
}
