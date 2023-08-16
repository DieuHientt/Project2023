package com.TMA.projectJava.service.Impl;

import com.hon.keycloak.entity.category;
import com.hon.keycloak.repository.categoryRepository;
import com.hon.keycloak.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public class categoryServiceImp implements categoryService {
    private final categoryRepository categoryRepository;
    @Autowired
    public categoryServiceImp(categoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    public List<category> getAllCategory(){
        return categoryRepository
                .findAll();
    }

    @Override
    public category saveCategory(category category) {
        categoryRepository.save(category);
        return category;
    }

    @Override
    public Object getCategory(BigInteger categoryId) {
        return categoryRepository
                .findById(categoryId)
                .orElse(null);
    }

    @Override
    public List<category> getCategoryNotDeleted() {
        return categoryRepository.findCategoryNotDeleted();
    }
    @Override
    public category updateCategory(BigInteger categoryId, Map<String, String> formData) {
        category existingCategory = categoryRepository.findById(categoryId).orElse(null);
        if (existingCategory != null) {  //Kiểm tra đối tượng có tồn tại
            String nameCategory = formData.get("name_category");
            existingCategory.setName_category(nameCategory);
            String status = formData.get("status");
            existingCategory.setStatus(status);
            return categoryRepository.save(existingCategory);
        }
        return null;
    }

}
//