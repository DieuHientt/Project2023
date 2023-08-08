package com.alibou.keycloak.controller;

import com.hon.keycloak.model.outcome;
import com.hon.keycloak.service.outcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/outcome")
public class outcomeController {
    @Autowired
    private final outcomeService outcomeService;

    public outcomeController(outcomeService outcomeService) {
        this.outcomeService = outcomeService;
    }

    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<outcome>> findAllOutcome() {
        return ResponseEntity.ok(outcomeService.getAllOutcome());
    }
    @GetMapping("/{cardId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<outcome> getOutcome(@PathVariable BigInteger outcomeId) {
        return ResponseEntity.ok((outcome) outcomeService.getOutcome(outcomeId));
    }

    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<outcome> saveOutcome(outcome outcome) {
        outcome savedOutcome = outcomeService.saveOutcome(outcome);
        return ResponseEntity.ok(savedOutcome);
    }
    @DeleteMapping("/{categogyId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<outcome>> deleteOutcome(@PathVariable BigInteger outcomeId) {
        outcomeService.deleteOutcome(outcomeId);
        return ResponseEntity.ok(outcomeService.getAllOutcome());
    }

    @PutMapping("/{categogyId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<outcome> updateOutcome(@PathVariable BigInteger outcomeId, @RequestParam Map<String, String> formData) {
        outcome updatedOutcomeResult =outcomeService.updateOutcome(outcomeId, formData);
        if (updatedOutcomeResult != null) {
            return ResponseEntity.ok(updatedOutcomeResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
