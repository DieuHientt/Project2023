package com.alibou.keycloak.repository;

import com.hon.keycloak.model.income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface incomeRepository extends JpaRepository<income, BigInteger> {
}
//