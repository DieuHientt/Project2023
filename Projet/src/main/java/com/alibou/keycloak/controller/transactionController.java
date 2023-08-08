package com.alibou.keycloak.controller;

import com.hon.keycloak.model.transactions;
import com.hon.keycloak.service.transactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class transactionController {
    @Autowired
    private final transactionService transactionService;

    public transactionController(transactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<transactions>> findAllTransaction() {
        return ResponseEntity.ok(transactionService.getAllTransaction());
    }
    @GetMapping("/{cardId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<transactions> getTransaction(@PathVariable BigInteger transactionId) {
        return ResponseEntity.ok((transactions) transactionService.getTransaction(transactionId));
    }
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<transactions> saveTransaction(transactions transactions) {
        transactions savedTransaction = transactionService.saveTransaction(transactions);
        return ResponseEntity.ok(savedTransaction);
    }
    @DeleteMapping("/{categogyId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<transactions>> deleteTransaction(@PathVariable BigInteger transactionId) {
        transactionService.deleteTransaction(transactionId);
        return ResponseEntity.ok(transactionService.getAllTransaction());
    }
    @PutMapping("/{categogyId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<transactions> updateTransaction(@PathVariable BigInteger transactionId, @RequestParam Map<String, String> formData) {
        transactions updatedTransactionResult =transactionService.updateTransaction(transactionId, formData);
        if (updatedTransactionResult != null) {
            return ResponseEntity.ok(updatedTransactionResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
