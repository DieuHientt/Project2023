package com.TMA.projectJava.repository;

import com.hon.keycloak.entity.card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface cardRepository extends JpaRepository<card, BigInteger> {
    @Query(value = "SELECT * FROM card WHERE status <> 'inactive'", nativeQuery = true)
    List<card> findCardNotDeleted();
}
