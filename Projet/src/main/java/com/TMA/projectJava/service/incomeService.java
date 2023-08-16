package com.TMA.projectJava.service;

import com.hon.keycloak.entity.income;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface incomeService {
    List<income> getAllIncome();
    income saveIncome(income income);

    Object getIncome(BigInteger incomeId);

    List<income> getIncomeNotDeleted();

    income updateIncome(BigInteger incomeId, Map<String, String> formData);
}
//