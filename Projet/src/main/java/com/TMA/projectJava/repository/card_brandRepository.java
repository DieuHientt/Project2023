package com.alibou.keycloak.repository;

import com.alibou.keycloak.Entity.card_brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface card_brandRepository extends JpaRepository<card_brand, BigInteger> {
}
