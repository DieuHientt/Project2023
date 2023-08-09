package com.alibou.keycloak.controller;

import com.hon.keycloak.log.Log;
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
    //Find All Income
    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<income>> findAllIncome() {
        Log.info("Find All Income Success");
        return ResponseEntity.ok(incomeService.getAllIncome());
    }
    //Find Income By ID
    @GetMapping("/{incomeId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<income> getIncome(@PathVariable BigInteger incomeId) {
        Log.info("Find Income Success");
        return ResponseEntity.ok((income) incomeService.getIncome(incomeId));
    }
    //Create New Income
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<income> saveIncome(income income) {
        income savedIncome = incomeService.saveIncome(income);
        Log.info("Create Income Success");
        return ResponseEntity.ok(savedIncome);
    }
    //Delete Income By ID
    @DeleteMapping("/{incomeId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<income>> deleteIncome(@PathVariable BigInteger incomeId) {
        incomeService.deleteIncome(incomeId);
        Log.info("Delete Income Success");
        return ResponseEntity.ok(incomeService.getAllIncome());
    }
    //Update Income By ID
    @PutMapping("/{incomeId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<income> updateIncome(@PathVariable BigInteger incomeId, @RequestParam Map<String, String> formData) {
        income updatedIncomeResult =incomeService.updateIncome(incomeId, formData);
        if (updatedIncomeResult != null) {
            Log.info("Update Income Success");
            return ResponseEntity.ok(updatedIncomeResult);
        } else {
            Log.error("Can Find Income Update");
            return ResponseEntity.notFound().build();
        }
    }
}
