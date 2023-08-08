package com.alibou.keycloak.service;

import com.hon.keycloak.model.income;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface incomeService {
    List<income> getAllIncome();
    income saveIncome(income income);

    Object getIncome(BigInteger incomeId);

    void deleteIncome(BigInteger incomeId);

    income updateIncome(BigInteger incomeId, Map<String, String> formData);
}
//