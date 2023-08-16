package com.TMA.projectJava.controller;


import com.hon.keycloak.entity.wallet;
import com.hon.keycloak.log.logger;
import com.hon.keycloak.service.walletService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Data
@RestController
@RequestMapping("/wallet")
public class walletController {

    @Autowired
    private  walletService walletService;
    //Find All Wallet
    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<wallet>> findAllWallet() {
        logger.info("Find All Wallet Success");
        return ResponseEntity.ok(walletService.getAllWallet());
    }
    //Find Wallet By ID
    @GetMapping("/{walletId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<wallet> getWallet(@PathVariable BigInteger walletId) {
        logger.info("Find Wallet Success");
        return ResponseEntity.ok((wallet) walletService.getWallet(walletId));
    }
    //Create Wallet
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<wallet> saveWallet(wallet wallet) {
        wallet savedWallet = walletService.saveWallet(wallet);
        logger.info("Create Wallet Success");
        return ResponseEntity.ok(savedWallet);
    }
    //Delete Wallet
    @GetMapping("/not-deleted")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<wallet>> getWalletNotDeleted() {
        List<wallet> notDeletedWallet = walletService.getWalletNotDeleted();
        logger.info("Delete Wallet Success");
        return ResponseEntity.ok(notDeletedWallet);
    }
    //Update Wallet By ID
    @PutMapping("/{walletId}")
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<wallet> updateWallet(@PathVariable BigInteger walletId, @RequestParam Map<String, String> formData) {
        wallet updatedWalletResult = walletService.updateWallet(walletId, formData);
        if (updatedWalletResult != null) {
            logger.info("Update Wallet Success");
            return ResponseEntity.ok(updatedWalletResult);
        } else {
            logger.error("Can Find Wallet Update");
            return ResponseEntity.notFound().build();
        }
    }

}
