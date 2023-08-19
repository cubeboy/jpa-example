package com.jinnara.customer.infra.repository;

import com.jinnara.customer.domain.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, Long> {
}
