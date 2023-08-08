package com.alibou.keycloak.service.Impl;

import com.hon.keycloak.model.outcome;
import com.hon.keycloak.repository.outcomeRepository;
import com.hon.keycloak.service.outcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
@Service
public class outcomeServiceImp implements outcomeService {
    private final outcomeRepository outcomeRepository;
    @Autowired
    public outcomeServiceImp(outcomeRepository outcomeRepository) {
        this.outcomeRepository = outcomeRepository;
    }
    public List<outcome> getAllOutcome(){
        return outcomeRepository
                .findAll();
    }

    @Override
    public outcome saveOutcome(outcome outcome) {
        outcomeRepository.save(outcome);
        return outcome;
    }

    @Override
    public Object getOutcome(BigInteger outcomeId) {
        return outcomeRepository
                .findById(outcomeId)
                .orElse(null);
    }

    @Override
    public void deleteOutcome(BigInteger outcomeId) {
        outcomeRepository.deleteById(outcomeId);
    }
    @Override
    public outcome updateOutcome(BigInteger outcomeId, Map<String, String> formData) {
        outcome existingOutcome = outcomeRepository.findById(outcomeId).orElse(null);
        if (existingOutcome != null) {
            String dateTime = formData.get("date_time");
            existingOutcome.setDate_time(dateTime);
            return outcomeRepository.save(existingOutcome);
        }
        return null;
    }
}
//