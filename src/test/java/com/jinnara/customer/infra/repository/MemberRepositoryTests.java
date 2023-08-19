package com.jinnara.customer.infra.repository;

import com.jinnara.customer.domain.dto.TempMember;
import com.jinnara.customer.domain.model.Member;
import com.jinnara.customer.domain.model.SmallMember;
import com.jinnara.customer.domain.model.Team;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Predicate;
import lombok.Builder;
import lombok.Getter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.convert.QueryByExamplePredicateBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class MemberRepositoryTests {
  Logger logger = LoggerFactory.getLogger(MemberRepositoryTests.class);
  @Autowired
  MemberRepository repository;

  @Autowired
  TeamRepository teamRepository;

  @Autowired
  SpecMemberRepository specRepository;

  Team mainTeam;

  @BeforeEach
  public void dataSetup() {
    Team team = Team.builder()
        .name("newTeam")
        .build();
    mainTeam = teamRepository.save(team);
  }

  @Test
  public void insertAndSelectSuccess() {
    Member member = Member.builder()
        .name("newMember")
        .city("seoul")
        .street("san 22")
        .zipCode("123456")
        .team(mainTeam)
        .build();
    Member savedMember = repository.save(member);

    assertTrue(savedMember.getMemberId() > 0);
    assertTrue(savedMember.getTeam().getTeamId() > 0);
    assertEquals("newTeam", savedMember.getTeam().getName());

    savedMember = repository.findById(savedMember.getMemberId()).orElse(null);

    assertNotNull(savedMember);

    assertEquals("newMember", savedMember.getName());
    assertEquals("seoul", savedMember.getCity());
    assertEquals(mainTeam.getTeamId(), savedMember.getTeam().getTeamId());
    assertEquals(mainTeam.getName(), savedMember.getTeam().getName());

    List<Member> members = repository.findByTeam(Team.builder().teamId(mainTeam.getTeamId()).build());
    assertEquals(1, members.size());
    assertEquals(mainTeam.getTeamId(), members.get(0).getTeam().getTeamId());
    assertEquals(mainTeam.getName(), members.get(0).getTeam().getName());
    logger.info(members.toString());
  }

  @Test
  @Sql("/insertMember.sql")
  public void pageableTest() {
    Sort sort = Sort.by(
        Sort.Order.desc("name"),
        Sort.Order.asc("memberId")
    );
    Pageable pageable = PageRequest.of(0, 5, sort);

    Page<Member> members = repository.findByNameLike("user%", pageable);

    assertEquals(4, members.getTotalPages());
    assertEquals(20, members.getTotalElements());

    assertEquals(5, members.getSize());
    assertEquals("user120", members.getContent().get(0).getName());
    assertEquals("user116", members.getContent().get(4).getName());
  }

  @Test
  @Sql("/insertMember.sql")
  public void dynamicQuery() {
    Member member = Member.builder()
        .name("user")
        .build();
    ExampleMatcher matcher = ExampleMatcher.matching()
        .withIgnoreNullValues()
        .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);
    Sort sort = Sort.by(
        Sort.Order.desc("name"),
        Sort.Order.asc("memberId")
    );
    Pageable pageable = PageRequest.of(0, 5, sort);

    Example<Member> example = Example.of(member, matcher);

    Page<SmallMember> members = repository.findBy(
        example,
        query -> query.sortBy(sort).page(pageable).map(it ->
          SmallMember.builder().memberId(it.getMemberId()).name(it.getName()).build()
        )
    );
    assertEquals(4, members.getTotalPages());
    assertEquals(20, members.getTotalElements());

    assertEquals(5, members.getSize());
    assertEquals("user120", members.getContent().get(0).getName());
    assertEquals("user116", members.getContent().get(4).getName());
  }

  @Test
  @Sql("/insertMember.sql")
  public void betweenQuery() {
    Sort sort = Sort.by(
        Sort.Order.asc("memberId"),
        Sort.Order.desc("name")
    );
    Pageable pageable = PageRequest.of(1, 5, sort);


    Specification<Member> spec = Specification.where(null);
    spec = spec.and((root, query, builder) ->
      builder.like(root.get("name"), "user%")
    );
    spec = spec.and((root, query, builder) -> builder.between(root.get("memberId"), 101, 110));

    Page<Member> members = specRepository.findAll(spec, pageable);
    assertEquals(2, members.getTotalPages());
    assertEquals(10, members.getTotalElements());

    assertEquals(5, members.getSize());

    assertEquals("user106", members.getContent().get(0).getName());
    assertEquals("user110", members.getContent().get(4).getName());

    assertTrue(members.hasPrevious());
    assertFalse(members.hasNext());
    assertEquals(5, members.getPageable().getOffset());

    logger.info("pageable info ==> {}", members.getPageable());
    logger.info("page info ==> {}", members);

    Page<TempMember> sMembers =  members.map(it ->
        TempMember.builder()
            .id(it.getMemberId())
            .username(it.getName())
            .build()
    );

    assertEquals(2, sMembers.getTotalPages());
    assertEquals(10, sMembers.getTotalElements());

    assertEquals(5, sMembers.getSize());

    assertEquals("user106", sMembers.getContent().get(0).getUsername());
    assertEquals("user110", sMembers.getContent().get(4).getUsername());

    logger.info("S pageable info ==> {}", sMembers.getPageable());
    logger.info("S page info ==> {}", sMembers);
  }

}
