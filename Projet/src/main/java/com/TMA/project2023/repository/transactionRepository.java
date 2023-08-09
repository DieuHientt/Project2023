package com.alibou.keycloak.repository;

import com.hon.keycloak.model.transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface transactionRepository extends JpaRepository<transactions, BigInteger> {
}
//