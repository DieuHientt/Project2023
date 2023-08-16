package com.TMA.projectJava.repository;

import com.hon.keycloak.entity.outcome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface outcomeRepository extends JpaRepository<outcome, BigInteger> {
    @Query(value = "SELECT * FROM outcome WHERE status <> 'inactive'", nativeQuery = true)
    List<outcome> findOutcomeNotDeleted();
}
