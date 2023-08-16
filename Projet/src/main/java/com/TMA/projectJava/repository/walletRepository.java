package com.TMA.projectJava.repository;


import com.hon.keycloak.entity.wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface walletRepository extends JpaRepository<wallet, BigInteger> {
    @Query(value = "SELECT * FROM wallet WHERE status <> 'inactive'", nativeQuery = true)
    List<wallet> findWalletNotDeleted();
}
