package com.jinnara.customer.infra.repository;

import com.jinnara.customer.config.QueryDslConfig;
import com.jinnara.customer.domain.model.Member;
import com.jinnara.customer.domain.model.QMember;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest(includeFilters = {
    @ComponentScan.Filter(
        type = FilterType.ASSIGNABLE_TYPE,
        classes = {QueryDslConfig.class})
})
@ExtendWith(SpringExtension.class)
public final class QueryDslTests {
  @Autowired
  EntityManager entityManager;

  @Autowired
  JPAQueryFactory factory;

  @Test
  @Sql("/insertMember.sql")
  public void entityManagerObjectCheck() {
    assertNotNull(entityManager);
    JPAQuery<Member> query = new JPAQuery<>(entityManager);

    QMember qMember = QMember.member;
    List<Member> members = query
        .select(Projections.fields(Member.class,
                qMember.memberId,
                qMember.name,
                qMember.gradeCode)
        )
        .from(qMember)
        .where(qMember.name.likeIgnoreCase(("user%")))
        .fetch();
    assertEquals(20, members.size());
  }

  @Test
  @Sql("/insertMember.sql")
  public void queryFactorySelect() {
    QMember qMember = QMember.member;
    List<Member> members = factory
        .select(
            Projections.fields(Member.class,
                qMember.memberId,
                qMember.name,
                qMember.gradeCode))
        .from(qMember)
        .where(qMember.name.likeIgnoreCase(("user%")))
        .fetch();
    assertEquals(20, members.size());
  }
}
