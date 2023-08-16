package com.TMA.projectJava.controller;


import com.hon.keycloak.entity.currency;
import com.hon.keycloak.log.logger;
import com.hon.keycloak.service.currencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/currency")
public class currencyController {

    @Autowired
    private currencyService currencyService;

    //Find All Currency
    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<currency>> findAllCurrency() {
        logger.info("Find All Currency Success");
        return ResponseEntity.ok(currencyService.getAllCurrency());
    }
    //Find Currency By ID
    @GetMapping("/{currencyId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<currency> getCurrency(@PathVariable BigInteger currencyId) {
        logger.info("Find Currency Success");
        return ResponseEntity.ok((currency) currencyService.getCurrency(currencyId));
    }
    //Create New Currency
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<currency> saveCardBrand(currency currency) {
        currency savedCurrency = currencyService.saveCurrency(currency);
        logger.info("Create Currency Success");
        return ResponseEntity.ok(savedCurrency);
    }
    //Delete Currency
    @GetMapping("/not-deleted")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<currency>> getCurrencyNotDeleted() {
        List<currency> notDeletedCurrency = currencyService.getCurrencyNotDeleted();
        logger.info("Delete Currency Success");
        return ResponseEntity.ok(notDeletedCurrency);
    }
    //Update Currency By ID
    @PutMapping("/{currencyId}")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<currency> updateCurrency(@PathVariable BigInteger currencyId, @RequestParam Map<String, String> formData) {
        currency updatedCurrencyResult = currencyService.updateCurrency(currencyId, formData);
        if (updatedCurrencyResult != null) {
            logger.info("Update Currency Success");
            return ResponseEntity.ok(updatedCurrencyResult);
        } else {
            logger.error("Can Find Currency Update");
            return ResponseEntity.notFound().build();
        }
    }

}