package com.TMA.projectJava.service;

import com.hon.keycloak.entity.category;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface categoryService {
    List<category> getAllCategory();
    category saveCategory(category category);

    Object getCategory(BigInteger categoryId);

    List<category> getCategoryNotDeleted();

    category updateCategory(BigInteger categoryId, Map<String, String> formData);
}
//