package com.TMA.projectJava.repository;

import com.hon.keycloak.entity.card_brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;


public interface card_brandRepository extends JpaRepository<card_brand, BigInteger> {
    @Query(value = "SELECT * FROM card_brand WHERE status <> 'inactive'", nativeQuery = true)
    List<card_brand> findCardBrandNotDeleted();
}
//