package com.alibou.keycloak.controller;

import com.alibou.keycloak.Entity.currency;
import com.alibou.keycloak.service.currencyService;
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


    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<currency>> findAllCurrency() {
        return ResponseEntity.ok(currencyService.getAllCurrency());
    }
    @GetMapping("/{currencyId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<currency> getCurrency(@PathVariable BigInteger currencyId) {
        return ResponseEntity.ok((currency) currencyService.getCurrency(currencyId));
    }
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<currency> saveCardBrand(currency currency) {
        currency savedCurrency = currencyService.saveCurrency(currency);
        return ResponseEntity.ok(savedCurrency);
    }
    @DeleteMapping("/{currencyId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<currency>> deleteCurrency(@PathVariable BigInteger currencyId) {
        currencyService.deleteCurrency(currencyId);
        return ResponseEntity.ok(currencyService.getAllCurrency());
    }
    @PutMapping("/{currencyId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<currency> updateCurrency(@PathVariable BigInteger currencyId, @RequestParam Map<String, String> formData) {
        currency updatedCurrencyResult = currencyService.updateCurrency(currencyId, formData);
        if (updatedCurrencyResult != null) {
            return ResponseEntity.ok(updatedCurrencyResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}