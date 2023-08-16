package com.TMA.projectJava.repository;


import com.hon.keycloak.entity.report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface reportRepository extends JpaRepository<report, BigInteger> {
    @Query(value = "SELECT * FROM report WHERE status <> 'inactive'", nativeQuery = true)
    List<report> findReportNotDeleted();
}
