package com.alibou.keycloak.controller;

import com.hon.keycloak.model.category;
import com.hon.keycloak.service.categoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
//
@RestController
@RequestMapping("/category")
public class categoryController {
    @Autowired
    private final categoryService categoryService;

    public categoryController(categoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<category>> findAllCategory() {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
    @GetMapping("/{cardId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<category> getCategory(@PathVariable BigInteger categoryId) {
        return ResponseEntity.ok((category) categoryService.getCategory(categoryId));
    }
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<category> saveCategory(category category) {
        category savedCategory = categoryService.saveCategory(category);
        return ResponseEntity.ok(savedCategory);
    }
    @DeleteMapping("/{categogyId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<category>> deleteCategory(@PathVariable BigInteger categoryId) {
        categoryService.deleteCategory(categoryId);
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
    @PutMapping("/{categogyId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<category> updateCategory(@PathVariable BigInteger categoryId, @RequestParam Map<String, String> formData) {
        category updatedCategoryResult = categoryService.updateCategory(categoryId, formData);
        if (updatedCategoryResult != null) {
            return ResponseEntity.ok(updatedCategoryResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
