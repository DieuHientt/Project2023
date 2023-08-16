package com.TMA.projectJava.controller;


import com.hon.keycloak.entity.saving_target;
import com.hon.keycloak.log.logger;
import com.hon.keycloak.service.saving_targetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/saving_target")
public class saving_targetController {


    @Autowired
    private saving_targetService savingTargetService;
    //Find All Saving Target
    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<saving_target>> findAllSavingTarget() {
        logger.info("Find All Saving Target Success");
        return ResponseEntity.ok(savingTargetService.getAllSavingTarget());
    }
    //Find Saving Target By ID
    @GetMapping("/{savingTargetId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<saving_target> getSavingTarget(@PathVariable BigInteger savingTargetId) {
        logger.info("Find Saving Target Success");
        return ResponseEntity.ok((saving_target) savingTargetService.getSavingTarget(savingTargetId));
    }
    //Create New Saving Target
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<saving_target> saveSavingTarget(saving_target savingTarget) {
        saving_target savedSavingTarget = savingTargetService.saveSavingTarget(savingTarget);
        logger.info("Create Saving Target Success");
        return ResponseEntity.ok(savedSavingTarget);
    }
    //Delete Transaction
    @GetMapping("/not-deleted")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<saving_target>> getSavingTargetNotDeleted() {
        List<saving_target> notDeletedSavingTarget = savingTargetService.getSavingTargetNotDeleted();
        logger.info("Delete Saving Target Success");
        return ResponseEntity.ok(notDeletedSavingTarget);
    }
    //Update Saving Target By ID
    @PutMapping("/{savingTargetId}")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<saving_target> updateSavingTarget(@PathVariable BigInteger savingTargetId, @RequestParam Map<String, String> formData) {
        saving_target updatedSavingTargetResult  = savingTargetService.updateSavingTarget(savingTargetId, formData);
        if (updatedSavingTargetResult != null) {
            logger.info("Update Saving Target Success");
            return ResponseEntity.ok(updatedSavingTargetResult);
        } else {
            logger.error("Can Find Saving Target Update");
            return ResponseEntity.notFound().build();
        }
    }

}