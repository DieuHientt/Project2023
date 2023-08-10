package com.alibou.keycloak.repository;

import com.alibou.keycloak.Entity.saving_target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
@Repository
public interface saving_targetRepository extends JpaRepository<saving_target, BigInteger> {
}
