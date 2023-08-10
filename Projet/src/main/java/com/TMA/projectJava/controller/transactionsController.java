package com.alibou.keycloak.controller;

import com.alibou.keycloak.Entity.transactions;
import com.alibou.keycloak.service.transactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transaction")
public class transactionsController {
    @Autowired
    private final transactionsService transactionsService;

    public transactionsController(transactionsService transactionsService) {
        this.transactionsService = transactionsService;
    }

    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<transactions>> findAllTransaction() {
        return ResponseEntity.ok(transactionsService.getAllTransaction());
    }
    @GetMapping("/{transactionsId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<transactions> getTransaction(@PathVariable BigInteger transactionId) {
        return ResponseEntity.ok((transactions) transactionsService.getTransaction(transactionId));
    }
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<transactions> saveTransaction(transactions transactions) {
        transactions savedTransaction = transactionsService.saveTransaction(transactions);
        return ResponseEntity.ok(savedTransaction);
    }
    @DeleteMapping("/{transactionsId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<transactions>> deleteTransaction(@PathVariable BigInteger transactionId) {
        transactionsService.deleteTransaction(transactionId);
        return ResponseEntity.ok(transactionsService.getAllTransaction());
    }
    @PutMapping("/{transactionsId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<transactions> updateTransaction(@PathVariable BigInteger transactionId, @RequestParam Map<String, String> formData) {
        transactions updatedTransactionResult = transactionsService.updateTransaction(transactionId, formData);
        if (updatedTransactionResult != null) {
            return ResponseEntity.ok(updatedTransactionResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
