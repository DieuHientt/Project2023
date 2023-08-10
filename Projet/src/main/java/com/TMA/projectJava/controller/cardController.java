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

@Data
@RestController
@RequestMapping("/card")
public class cardController {

    @Autowired
    private final cardRepository cardRepository;
    @Autowired
    private final card_brandRepository card_brandRepository;

    @Autowired
    private final cardService cardService;

    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<card>> findAllCard() {
        return ResponseEntity.ok(cardService.getAllCard());
    }

    @GetMapping("/{cardId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<card> getCard(@PathVariable BigInteger cardId) {
        return ResponseEntity.ok((card) cardService.getCard(cardId));
    }
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<card> saveCardBrand(card card) {
        card savedCard = cardService.saveCard(card);
        return ResponseEntity.ok(savedCard);
    }
    @DeleteMapping("/{cardId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<card>> deleteCard(@PathVariable BigInteger cardId) {
        cardService.deleteCard(cardId);
        return ResponseEntity.ok(cardService.getAllCard());
    }
    @PutMapping("/{cardId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<card> updateCard(@PathVariable BigInteger cardId, @RequestParam Map<String, String> formData) {
        card updatedCardResult = cardService.updateCard(cardId, formData);
        if (updatedCardResult != null) {
            return ResponseEntity.ok(updatedCardResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{cardId}/card_brand/{card_brandId}")
    card addCardToCard_brand(
            @PathVariable BigInteger cardId,
            @PathVariable BigInteger card_brandId
    ) {
        card card = cardRepository.findById(cardId).get();
        card_brand card_brand = card_brandRepository.findById(card_brandId).get();
        card.setCard_brand(card_brand);
        return cardRepository.save(card);
    }

}