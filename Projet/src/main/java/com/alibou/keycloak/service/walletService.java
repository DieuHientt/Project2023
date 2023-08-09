package com.alibou.keycloak.service;

import com.alibou.keycloak.entity.wallet;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public interface walletService {
    List<wallet> getAllWallet();
    wallet saveWallet(wallet wallet);

    Object getWallet(BigInteger walletId);

    void deleteWallet(BigInteger walletId);

    wallet updateWallet(BigInteger walletId, Map<String, String> formData);
}
