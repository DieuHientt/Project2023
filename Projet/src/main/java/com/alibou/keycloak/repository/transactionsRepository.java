package com.alibou.keycloak.repository;

import com.alibou.keycloak.entity.transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;


@Repository
public interface transactionsRepository extends JpaRepository<transactions, BigInteger> {
}
