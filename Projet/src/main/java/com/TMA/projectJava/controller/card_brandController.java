package com.alibou.keycloak.controller;

import com.alibou.keycloak.Entity.card_brand;
import com.alibou.keycloak.repository.cardRepository;
import com.alibou.keycloak.repository.card_brandRepository;
import com.alibou.keycloak.service.card_brandService;
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
    private  card_brandService card_brandService;
    @Autowired
    private  cardRepository cardRepository;
    @Autowired
    private  card_brandRepository card_brandRepository;

// find all card, role: admin
    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<card_brand>> findAllCardBrand() {
        return ResponseEntity.ok(card_brandService.getAllCardBrand());
    }

    @GetMapping("/{cardBrandId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<card_brand> getCardBrand(@PathVariable BigInteger cardBrandId) {
        return ResponseEntity.ok((card_brand) card_brandService.getCardBrand(cardBrandId));
    }
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<card_brand> saveCardBrand(card_brand cardBrand) {
        card_brand savedCardBrand = card_brandService.saveCardBrand(cardBrand);
        return ResponseEntity.ok(savedCardBrand);
    }
    @DeleteMapping("/{cardBrandId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<card_brand>> deleteCardBrand(@PathVariable BigInteger cardBrandId) {
        card_brandService.deleteCardBrand(cardBrandId);
        return ResponseEntity.ok(card_brandService.getAllCardBrand());
    }
    @PutMapping("/{cardBrandId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<card_brand> updateCardBrand(@PathVariable BigInteger cardBrandId, @RequestParam Map<String, String> formData) {
        card_brand updatedCardBrandResult = card_brandService.updateCardBrand(cardBrandId, formData);
        if (updatedCardBrandResult != null) {
            return ResponseEntity.ok(updatedCardBrandResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}