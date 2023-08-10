package com.alibou.keycloak.repository;

import com.alibou.keycloak.Entity.user_model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
@Repository
public interface user_modelRepository extends JpaRepository<user_model, BigInteger> {
}
