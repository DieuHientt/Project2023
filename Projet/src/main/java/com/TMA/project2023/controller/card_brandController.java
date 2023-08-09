package com.alibou.keycloak.controller;

import com.hon.keycloak.log.Log;
import com.hon.keycloak.model.card_brand;
import com.hon.keycloak.service.card_brandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/card_brand")
public class card_brandController {
    @Autowired
    private final card_brandService cardBrandService;

    public card_brandController(card_brandService cardBrandService) {
        this.cardBrandService = cardBrandService;
    }
    //Find All Card Brand
    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<card_brand>> findAllCardBrand() {
        Log.info("Find All Card Brand Success");
        return ResponseEntity.ok(cardBrandService.getAllCardBrand());
    }
    //Find Card Brand By ID
    @GetMapping("/{cardBrandId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<card_brand> getCardBrand(@PathVariable BigInteger cardBrandId) {
        Log.info("Find Card Brand Success");
        return ResponseEntity.ok((card_brand) cardBrandService.getCardBrand(cardBrandId));
    }
    //Create New Card Brand
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<card_brand> saveCardBrand(card_brand cardBrand) {
        card_brand savedCardBrand = cardBrandService.saveCardBrand(cardBrand);
        Log.info("Create Card Brand Success");
        return ResponseEntity.ok(savedCardBrand);
    }
    //Delete Card Brand By ID
    @DeleteMapping("/{cardBrandId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<card_brand>> deleteCardBrand(@PathVariable BigInteger cardBrandId) {
        cardBrandService.deleteCardBrand(cardBrandId);
        Log.info("Delete Card Brand Success");
        return ResponseEntity.ok(cardBrandService.getAllCardBrand());
    }
    //Update Card Brand By ID
    @PutMapping("/{cardBrandId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<card_brand> updateCardBrand(@PathVariable BigInteger cardBrandId, @RequestParam Map<String, String> formData) {
        card_brand updatedCardBrandResult = cardBrandService.updateCardBrand(cardBrandId, formData);
        if (updatedCardBrandResult != null) {
            Log.info("Update Card Brand Success");
            return ResponseEntity.ok(updatedCardBrandResult);
        } else {
            Log.error("Can Find Brand Card Update");
            return ResponseEntity.notFound().build();
        }
    }

}
