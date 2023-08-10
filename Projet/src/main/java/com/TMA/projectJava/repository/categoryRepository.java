package com.alibou.keycloak.repository;


import com.alibou.keycloak.Entity.category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigInteger;

public interface categoryRepository extends JpaRepository<category, BigInteger> {
}
//