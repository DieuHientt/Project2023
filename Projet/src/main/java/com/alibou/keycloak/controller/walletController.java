package com.alibou.keycloak.controller;

import com.alibou.keycloak.entity.wallet;
import com.alibou.keycloak.service.walletService;
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

    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<wallet>> findAllWallet() {
        return ResponseEntity.ok(walletService.getAllWallet());
    }

    @GetMapping("/{walletId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<wallet> getWallet(@PathVariable BigInteger walletId) {
        return ResponseEntity.ok((wallet) walletService.getWallet(walletId));
    }
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<wallet> saveWallet(wallet wallet) {
        wallet savedWallet = walletService.saveWallet(wallet);
        return ResponseEntity.ok(savedWallet);
    }
    @DeleteMapping("/{walletId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<wallet>> deleteCard(@PathVariable BigInteger walletId) {
        walletService.deleteWallet(walletId);
        return ResponseEntity.ok(walletService.getAllWallet());
    }
    @PutMapping("/{walletId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<wallet> updateWallet(@PathVariable BigInteger walletId, @RequestParam Map<String, String> formData) {
        wallet updatedWalletResult = walletService.updateWallet(walletId, formData);
        if (updatedWalletResult != null) {
            return ResponseEntity.ok(updatedWalletResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
