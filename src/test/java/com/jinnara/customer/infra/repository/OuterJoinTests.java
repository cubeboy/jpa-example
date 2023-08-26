package com.jinnara.customer.infra.repository;

import com.jinnara.customer.domain.model.Member;
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
public class OuterJoinTests {
  Logger logger = LoggerFactory.getLogger(OuterJoinTests.class);
  @Autowired
  MemberRepository repository;

  @Test
  @Sql("/insertMember.sql")
  public void joinTableSelect() {
    Member member = repository.findById(101L).get();
    assertEquals(101L, member.getMemberId());
    assertEquals(201L, member.getTeam().getTeamId());
    assertEquals("FirstTeam", member.getTeam().getName());
    assertEquals("02", member.getLevel().getCodeId());

    assertEquals("GRADE", member.getGrade().getGroupId());
    assertEquals("A", member.getGrade().getCodeId());
    assertEquals("A Grade", member.getGrade().getCodeName());

    assertEquals("LEVEL" , member.getLevel().getGroupId());
    assertEquals("02", member.getLevel().getCodeId());
    assertEquals("Silver", member.getLevel().getCodeName());
  }

  @Test
  @Sql("/insertMember.sql")
  public void joinTableInsert() {
    Member member = Member.builder()
        .gradeCode("C")
        .levelCode("03")
        .name("Join User")
        .build();

    member = repository.saveAndFlush(member);
    logger.info("insertedMemberId ==> {}" , member.getMemberId());
    assertTrue(0 < member.getMemberId());


    Member savedMember = repository.findById(101L).get();
//    assertNull(savedMember.getTeam());

    assertEquals("GRADE", savedMember.getGrade().getGroupId());
    assertEquals("A", savedMember.getGrade().getCodeId());
    assertEquals("A Grade", savedMember.getGrade().getCodeName());

    assertEquals("LEVEL" , savedMember.getLevel().getGroupId());
    assertEquals("02", savedMember.getLevel().getCodeId());
    assertEquals("Silver", savedMember.getLevel().getCodeName());

  }
}
