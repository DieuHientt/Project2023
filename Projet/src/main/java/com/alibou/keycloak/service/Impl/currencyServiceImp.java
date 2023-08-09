package com.alibou.keycloak.service.Impl;

import com.alibou.keycloak.entity.currency;
import com.alibou.keycloak.repository.currencyRepository;
import com.alibou.keycloak.service.currencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public class currencyServiceImp  implements currencyService {
    private currencyRepository currencyRepository;

    @Autowired
    public currencyServiceImp(currencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<currency> getAllCurrency() {
        return currencyRepository
                .findAll();
    }

    @Override
    public currency saveCurrency(currency currency) {
        currencyRepository.save(currency);
        return currency;
    }

    @Override
    public Object getCurrency(BigInteger currencyId) {
        return currencyRepository
                .findById(currencyId)
                .orElse(null);
    }

    @Override
    public void deleteCurrency(BigInteger currencyId) {
        currencyRepository.deleteById(currencyId);
    }
    @Override
    public currency updateCurrency(BigInteger currencyId, Map<String, String> formData) {
        currency existingCurrency = currencyRepository.findById(currencyId).orElse(null);
        if (existingCurrency != null) {
            String name_currency = formData.get("name_currency");
            existingCurrency.setName_currency(name_currency);
            return currencyRepository.save(existingCurrency);
        }
        return null;
    }
}