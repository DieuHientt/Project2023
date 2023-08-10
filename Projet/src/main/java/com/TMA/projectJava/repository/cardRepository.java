package com.alibou.keycloak.repository;

import com.alibou.keycloak.Entity.card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
@Repository
public interface cardRepository extends JpaRepository<card, BigInteger> {
}
