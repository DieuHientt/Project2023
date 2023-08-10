package com.alibou.keycloak.repository;


import com.alibou.keycloak.Entity.income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface incomeRepository extends JpaRepository<income, BigInteger> {
}
//