package com.TMA.projectJava.service;


import com.hon.keycloak.entity.wallet;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public interface walletService {
    List<wallet> getAllWallet();
    wallet saveWallet(wallet wallet);

    Object getWallet(BigInteger walletId);

    List<wallet> getWalletNotDeleted();

    wallet updateWallet(BigInteger walletId, Map<String, String> formData);
}
