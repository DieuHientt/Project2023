package com.alibou.keycloak.controller;

import com.hon.keycloak.model.income;
import com.hon.keycloak.service.incomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/income")
public class incomeController {
    @Autowired
    private final incomeService incomeService;

    public incomeController(incomeService incomeService) {
        this.incomeService = incomeService;
    }

    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<income>> findAllIncome() {
        return ResponseEntity.ok(incomeService.getAllIncome());
    }
    @GetMapping("/{cardId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<income> getIncome(@PathVariable BigInteger incomeId) {
        return ResponseEntity.ok((income) incomeService.getIncome(incomeId));
    }
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<income> saveIncome(income income) {
        income savedIncome = incomeService.saveIncome(income);
        return ResponseEntity.ok(savedIncome);
    }
    @DeleteMapping("/{categogyId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<income>> deleteIncome(@PathVariable BigInteger incomeId) {
        incomeService.deleteIncome(incomeId);
        return ResponseEntity.ok(incomeService.getAllIncome());
    }
    @PutMapping("/{categogyId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<income> updateIncome(@PathVariable BigInteger incomeId, @RequestParam Map<String, String> formData) {
        income updatedIncomeResult =incomeService.updateIncome(incomeId, formData);
        if (updatedIncomeResult != null) {
            return ResponseEntity.ok(updatedIncomeResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
