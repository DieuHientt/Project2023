package com.alibou.keycloak.service.Impl;

import com.hon.keycloak.model.income;
import com.hon.keycloak.repository.incomeRepository;
import com.hon.keycloak.service.incomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
@Service
public class incomeServiceImp implements incomeService {
    private final incomeRepository incomeRepository;
    @Autowired
    public incomeServiceImp(incomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }
    public List<income> getAllIncome(){
        return incomeRepository
                .findAll();
    }

    @Override
    public income saveIncome(income income) {
        incomeRepository.save(income);
        return income;
    }

    @Override
    public Object getIncome(BigInteger incomeId) {
        return incomeRepository
                .findById(incomeId)
                .orElse(null);
    }

    @Override
    public void deleteIncome(BigInteger incomeId) {
        incomeRepository.deleteById(incomeId);
    }
    @Override
    public income updateIncome(BigInteger incomeId, Map<String, String> formData) {
        income existingIncome = incomeRepository.findById(incomeId).orElse(null);
        if (existingIncome != null) {
           String dateTime = formData.get("date_time");
            existingIncome.setDate_time(dateTime);
            return incomeRepository.save(existingIncome);
        }
        return null;
    }
}
//