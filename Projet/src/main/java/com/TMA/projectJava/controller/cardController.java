package com.alibou.keycloak.controller;


import com.alibou.keycloak.Entity.card;
import com.alibou.keycloak.Entity.card_brand;
import com.alibou.keycloak.repository.cardRepository;
import com.alibou.keycloak.repository.card_brandRepository;
import com.alibou.keycloak.service.cardService;
import lombok.Data;
import org.slf4j.ILoggerFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

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
        logger.info("Find All Card Success");
        return ResponseEntity.ok(cardService.getAllCard());
    }
    //Find Card By ID
    @GetMapping("/{cardId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<card> getCard(@PathVariable BigInteger cardId) {
        logger.info("Find Card Success");
        return ResponseEntity.ok((card) cardService.getCard(cardId));
    }
    //Create New Card
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<card> saveCard(card card) {
        card savedCard = cardService.saveCard(card);
        logger.info("Create Card Success");
        return ResponseEntity.ok(savedCard);
    }
    //Delete Card By ID
    @DeleteMapping("/{cardId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<card>> deleteCard(@PathVariable BigInteger cardId) {
        cardService.deleteCard(cardId);
        logger.info("Delete Card Success");
        return ResponseEntity.ok(cardService.getAllCard());
    }
    //Update Card By ID
    @PutMapping("/{cardId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<card> updateCard(@PathVariable BigInteger cardId, @RequestParam Map<String, String> formData) {
        card updatedCardResult = cardService.updateCard(cardId, formData);
        if (updatedCardResult != null) {
            logger.info("Update Card Success");
            return ResponseEntity.ok(updatedCardResult);
        } else {
            logger.error("Can Find Card Update");
            return ResponseEntity.notFound().build();
        }
    }
}