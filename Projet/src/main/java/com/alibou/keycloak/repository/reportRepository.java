package com.alibou.keycloak.repository;

import com.alibou.keycloak.entity.report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.math.BigInteger;
@Repository
public interface reportRepository extends JpaRepository<report, BigInteger> {
}
