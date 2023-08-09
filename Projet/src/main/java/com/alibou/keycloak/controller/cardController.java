package com.alibou.keycloak.controller;

import com.hon.keycloak.log.Log;
import com.hon.keycloak.model.card;
import com.hon.keycloak.service.cardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
//
@RestController
@RequestMapping("/card")
public class cardController {
    @Autowired
    private final cardService cardService;
    public cardController(com.hon.keycloak.service.cardService cardService) {
        this.cardService = cardService;
    }
    //Find All Card
    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<card>> findAllCard() {
        Log.info("Find All Card Success");
        return ResponseEntity.ok(cardService.getAllCard());
    }
    //Find Card By ID
    @GetMapping("/{cardId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<card> getCard(@PathVariable BigInteger cardId) {
        Log.info("Find Card Success");
        return ResponseEntity.ok((card) cardService.getCard(cardId));
    }
    //Create New Card
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<card> saveCard(card card) {
        card savedCard = cardService.saveCard(card);
        Log.info("Create Card Success");
        return ResponseEntity.ok(savedCard);
    }
    //Delete Card By ID
    @DeleteMapping("/{cardId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<card>> deleteCard(@PathVariable BigInteger cardId) {
        cardService.deleteCard(cardId);
        Log.info("Delete Card Success");
        return ResponseEntity.ok(cardService.getAllCard());
    }
    //Update Card By ID
    @PutMapping("/{cardId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<card> updateCard(@PathVariable BigInteger cardId, @RequestParam Map<String, String> formData) {
        card updatedCardResult = cardService.updateCard(cardId, formData);
        if (updatedCardResult != null) {
            Log.info("Update Card Success");
            return ResponseEntity.ok(updatedCardResult);
        } else {
            Log.error("Can Find Card Update");
            return ResponseEntity.notFound().build();
        }
    }
}
