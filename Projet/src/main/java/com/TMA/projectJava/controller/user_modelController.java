package com.alibou.keycloak.controller;

import com.alibou.keycloak.Entity.user_model;
import com.alibou.keycloak.service.user_modelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user_model")
public class user_modelController  {

    @Autowired
    private user_modelService userModelService;

    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<user_model>> findAllUserModel() {
        return ResponseEntity.ok(userModelService.getAllUserModel());
    }
    @GetMapping("/{userModelId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<user_model> getUserModel(@PathVariable BigInteger userModelId) {
        return ResponseEntity.ok((user_model) userModelService.getUserModel(userModelId));
    }
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<user_model> saveUserModel(user_model userModel) {
        user_model savedUserModel = userModelService.saveUserModel(userModel);
        return ResponseEntity.ok(savedUserModel);
    }
    @DeleteMapping("/{userModelId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<user_model>> deleteUserModel(@PathVariable BigInteger userModelId) {
        userModelService.deleteUserModel(userModelId);
        return ResponseEntity.ok(userModelService.getAllUserModel());
    }
    @PutMapping("/{userModelId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<user_model> updateUserModel(@PathVariable BigInteger userModelId, @RequestParam Map<String, String> formData) {
        user_model updatedReportResult  = userModelService.updateUserModel(userModelId, formData);
        if (updatedReportResult != null) {
            return ResponseEntity.ok(updatedReportResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}