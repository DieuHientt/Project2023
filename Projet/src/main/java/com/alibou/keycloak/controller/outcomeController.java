package com.alibou.keycloak.controller;

import com.hon.keycloak.log.Log;
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
    //Find All Outcome
    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<outcome>> findAllOutcome() {
        Log.info("Find All Outcome Success");
        return ResponseEntity.ok(outcomeService.getAllOutcome());
    }
    //Find Outcome By ID
    @GetMapping("/{outcomeId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<outcome> getOutcome(@PathVariable BigInteger outcomeId) {
        Log.info("Find Outcome Success");
        return ResponseEntity.ok((outcome) outcomeService.getOutcome(outcomeId));
    }
    //Create New Outcome
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<outcome> saveOutcome(outcome outcome) {
        outcome savedOutcome = outcomeService.saveOutcome(outcome);
        Log.info("Create Outcome Success");
        return ResponseEntity.ok(savedOutcome);
    }
    //Delete Outcome By ID
    @DeleteMapping("/{outcomeId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<outcome>> deleteOutcome(@PathVariable BigInteger outcomeId) {
        outcomeService.deleteOutcome(outcomeId);
        Log.info("Delete Outcome Success");
        return ResponseEntity.ok(outcomeService.getAllOutcome());
    }
    //Update Outcome By ID
    @PutMapping("/{outcomeId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<outcome> updateOutcome(@PathVariable BigInteger outcomeId, @RequestParam Map<String, String> formData) {
        outcome updatedOutcomeResult =outcomeService.updateOutcome(outcomeId, formData);
        if (updatedOutcomeResult != null) {
            Log.info("Update Outcome Success");
            return ResponseEntity.ok(updatedOutcomeResult);
        } else {
            Log.error("Can Find Outcome Update");
            return ResponseEntity.notFound().build();
        }
    }
}
