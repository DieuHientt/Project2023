package com.TMA.projectJava.repository;

import com.hon.keycloak.entity.income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface incomeRepository extends JpaRepository<income, BigInteger> {
    @Query(value = "SELECT * FROM income WHERE status <> 'inactive'", nativeQuery = true)
    List<income> findIncomeNotDeleted();
}
