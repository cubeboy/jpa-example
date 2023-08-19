package com.jinnara.customer.infra.repository;

import com.jinnara.customer.domain.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SpecMemberRepository extends JpaRepository<Member, Long>, JpaSpecificationExecutor<Member> {

}
