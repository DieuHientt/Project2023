package com.alibou.keycloak.service;

import com.alibou.keycloak.entity.currency;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;


public interface currencyService {
    List<currency> getAllCurrency();
    currency saveCurrency(currency currency);

    Object getCurrency(BigInteger currencyId);

    void deleteCurrency(BigInteger currencyId);

    currency updateCurrency(BigInteger currencyId, Map<String, String> formData);
}
