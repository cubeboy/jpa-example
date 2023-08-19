package com.jinnara.customer.infra.repository;

import com.jinnara.customer.domain.model.Member;
import com.jinnara.customer.domain.model.Team;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

public interface MemberRepository extends
    JpaRepository<Member, Long>,
    QueryByExampleExecutor<Member>,
    JpaSpecificationExecutor<Member> {
  List<Member> findByTeam(Team team);

  Page<Member> findByNameLike(String name, Pageable pageable);

}
