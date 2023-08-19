package com.jinnara.customer.infra.repository;

import com.jinnara.customer.domain.model.SmallMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SmallMemberRepository extends JpaRepository<SmallMember, Long> {
}
