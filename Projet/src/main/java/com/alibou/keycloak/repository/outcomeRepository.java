package com.alibou.keycloak.repository;

import com.hon.keycloak.model.outcome;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface outcomeRepository extends JpaRepository<outcome, BigInteger> {
}
//