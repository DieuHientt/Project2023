package com.TMA.projectJava.service;


import com.hon.keycloak.entity.currency;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;


public interface currencyService {
    List<currency> getAllCurrency();
    currency saveCurrency(currency currency);

    Object getCurrency(BigInteger currencyId);

    List<currency> getCurrencyNotDeleted();

    currency updateCurrency(BigInteger currencyId, Map<String, String> formData);
}
