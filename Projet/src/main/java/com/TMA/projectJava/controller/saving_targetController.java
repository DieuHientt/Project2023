package com.alibou.keycloak.controller;

import com.alibou.keycloak.Entity.saving_target;
import com.alibou.keycloak.service.saving_targetService;
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

    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<saving_target>> findAllSavingTarget() {
        return ResponseEntity.ok(savingTargetService.getAllSavingTarget());
    }
    @GetMapping("/{savingTargetId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<saving_target> getSavingTarget(@PathVariable BigInteger savingTargetId) {
        return ResponseEntity.ok((saving_target) savingTargetService.getSavingTarget(savingTargetId));
    }
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<saving_target> saveSavingTarget(saving_target savingTarget) {
        saving_target savedSavingTarget = savingTargetService.saveSavingTarget(savingTarget);
        return ResponseEntity.ok(savedSavingTarget);
    }
    @DeleteMapping("/{savingTargetId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<saving_target>> deleteSavingTarget(@PathVariable BigInteger savingTargetId) {
        savingTargetService.deleteSavingTarget(savingTargetId);
        return ResponseEntity.ok(savingTargetService.getAllSavingTarget());
    }
    @PutMapping("/{savingTargetId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<saving_target> updateSavingTarget(@PathVariable BigInteger savingTargetId, @RequestParam Map<String, String> formData) {
        saving_target updatedSavingTargetResult  = savingTargetService.updateSavingTarget(savingTargetId, formData);
        if (updatedSavingTargetResult != null) {
            return ResponseEntity.ok(updatedSavingTargetResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}