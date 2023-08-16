package com.TMA.projectJava.controller;

import com.hon.keycloak.entity.category;
import com.hon.keycloak.log.logger;
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
        logger.info("Find All Category Success");
        return ResponseEntity.ok(categoryService.getAllCategory());
    }
    //Find Category By ID
    @GetMapping("/{categoryId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<category> getCategory(@PathVariable BigInteger categoryId) {
        logger.info("Find Category Success");
        return ResponseEntity.ok((category) categoryService.getCategory(categoryId));
    }
    //Create New Category
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<category> saveCategory(category category) {
        category savedCategory = categoryService.saveCategory(category);
        logger.info("Create Category Success");
        return ResponseEntity.ok(savedCategory);
    }
    //Delete Category
    @GetMapping("/not-deleted")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<category>> getCategoryNotDeleted() {
        List<category> notDeletedCategory = categoryService.getCategoryNotDeleted();
        logger.info("Delete Category Success");
        return ResponseEntity.ok(notDeletedCategory);
    }
    //Update Category By ID
    @PutMapping("/{categoryId}")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<category> updateCategory(@PathVariable BigInteger categoryId, @RequestParam Map<String, String> formData) {
        category updatedCategoryResult = categoryService.updateCategory(categoryId, formData);
        if (updatedCategoryResult != null) {
            logger.info("Update Category Success");
            return ResponseEntity.ok(updatedCategoryResult);
        } else {
            logger.error("Can Find Category Update");
            return ResponseEntity.notFound().build();
        }
    }
}
