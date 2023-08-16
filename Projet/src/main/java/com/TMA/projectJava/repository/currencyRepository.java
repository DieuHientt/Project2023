package com.TMA.projectJava.repository;


import com.hon.keycloak.entity.currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface currencyRepository extends JpaRepository<currency, BigInteger> {
    @Query(value = "SELECT * FROM currency WHERE status <> 'inactive'", nativeQuery = true)
    List<currency> findCurrencyNotDeleted();
}
