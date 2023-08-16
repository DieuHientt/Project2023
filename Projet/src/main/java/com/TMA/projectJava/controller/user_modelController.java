package com.TMA.projectJava.controller;


import com.hon.keycloak.entity.user_model;
import com.hon.keycloak.log.logger;
import com.hon.keycloak.service.user_modelService;
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
    //Find All User Model
    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<user_model>> findAllUserModel() {
        logger.info("Find All User Model Success");
        return ResponseEntity.ok(userModelService.getAllUserModel());
    }
    //Find User Model By ID
    @GetMapping("/{userModelId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<user_model> getUserModel(@PathVariable BigInteger userModelId) {
        logger.info("Find User Model Success");
        return ResponseEntity.ok((user_model) userModelService.getUserModel(userModelId));
    }
    //Create New User Model
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<user_model> saveUserModel(user_model userModel) {
        user_model savedUserModel = userModelService.saveUserModel(userModel);
        logger.info("Create User Model Success");
        return ResponseEntity.ok(savedUserModel);
    }
    //Delete User Model
    @GetMapping("/not-deleted")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<user_model>> getUserModelNotDeleted() {
        List<user_model> notDeletedUserModel = userModelService.getUserModelNotDeleted();
        logger.info("Delete User Model Success");
        return ResponseEntity.ok(notDeletedUserModel);
    }
    //Update User Model By ID
    @PutMapping("/{userModelId}")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<user_model> updateUserModel(@PathVariable BigInteger userModelId, @RequestParam Map<String, String> formData) {
        user_model updatedReportResult  = userModelService.updateUserModel(userModelId, formData);
        if (updatedReportResult != null) {
            logger.info("Update User Model Success");
            return ResponseEntity.ok(updatedReportResult);
        } else {
            logger.error("Can Find User Model Update");
            return ResponseEntity.notFound().build();
        }
    }

}