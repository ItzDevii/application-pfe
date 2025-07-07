package com.supemir.association.repository;

import com.supemir.association.entity.Contribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CotisationRepository extends JpaRepository<Contribution, Long> {
}
