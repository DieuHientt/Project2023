package com.alibou.keycloak.repository;


import com.alibou.keycloak.Entity.transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface transactionRepository extends JpaRepository<transactions, BigInteger> {
}
//