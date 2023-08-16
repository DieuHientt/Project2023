package com.TMA.projectJava.controller;

import com.hon.keycloak.entity.transactions;
import com.hon.keycloak.log.logger;
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
    //Find All Transaction
    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<transactions>> findAllTransaction() {
        logger.info("Find All Transaction Success");
        return ResponseEntity.ok(transactionService.getAllTransaction());
    }
    //Find Transaction By ID
    @GetMapping("/{transactionId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<transactions> getTransaction(@PathVariable BigInteger transactionId) {
        logger.info("Find Transaction Success");
        return ResponseEntity.ok((transactions) transactionService.getTransaction(transactionId));
    }
    //Create New Transaction
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<transactions> saveTransaction(transactions transactions) {
        transactions savedTransaction = transactionService.saveTransaction(transactions);
        logger.info("Create Transaction Success");
        return ResponseEntity.ok(savedTransaction);
    }
    //Delete Transaction
    @GetMapping("/not-deleted")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<transactions>> getTransactionNotDeleted() {
        List<transactions> notDeletedTransaction = transactionService.getTransactionNotDeleted();
        logger.info("Delete Transaction Success");
        return ResponseEntity.ok(notDeletedTransaction);
    }
    //Update Transaction By ID
    @PutMapping("/{transactionId}")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<transactions> updateTransaction(@PathVariable BigInteger transactionId, @RequestParam Map<String, String> formData) {
        transactions updatedTransactionResult =transactionService.updateTransaction(transactionId, formData);
        if (updatedTransactionResult != null) {
            logger.info("Update Transaction Success");
            return ResponseEntity.ok(updatedTransactionResult);
        } else {
            logger.error("Can Find Transaction Update");
            return ResponseEntity.notFound().build();
        }
    }
}
