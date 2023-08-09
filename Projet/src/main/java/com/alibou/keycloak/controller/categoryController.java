package com.alibou.keycloak.controller;

import com.hon.keycloak.log.Log;
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
    //Find All Category
    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<category>> findAllCategory() {
        Log.info("Find All Category Success");
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
    //Find Category By ID
    @GetMapping("/{categoryId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<category> getCategory(@PathVariable BigInteger categoryId) {
        Log.info("Find Category Success");
        return ResponseEntity.ok((category) categoryService.getCategory(categoryId));
    }
    //Create New Category
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<category> saveCategory(category category) {
        category savedCategory = categoryService.saveCategory(category);
        Log.info("Create Category Success");
        return ResponseEntity.ok(savedCategory);
    }
    //Delete Category By ID
    @DeleteMapping("/{categoryId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<category>> deleteCategory(@PathVariable BigInteger categoryId) {
        categoryService.deleteCategory(categoryId);
        Log.info("Delete Category Success");
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
    //Update Category By ID
    @PutMapping("/{categoryId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<category> updateCategory(@PathVariable BigInteger categoryId, @RequestParam Map<String, String> formData) {
        category updatedCategoryResult = categoryService.updateCategory(categoryId, formData);
        if (updatedCategoryResult != null) {
            Log.info("Update Category Success");
            return ResponseEntity.ok(updatedCategoryResult);
        } else {
            Log.error("Can Find Category Update");
            return ResponseEntity.notFound().build();
        }
    }
}
