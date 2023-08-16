package com.TMA.projectJava.repository;

import com.hon.keycloak.entity.category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigInteger;
import java.util.List;

public interface categoryRepository extends JpaRepository<category, BigInteger> {
    @Query(value = "SELECT * FROM category WHERE status <> 'inactive'", nativeQuery = true)
    List<category> findCategoryNotDeleted();
}
