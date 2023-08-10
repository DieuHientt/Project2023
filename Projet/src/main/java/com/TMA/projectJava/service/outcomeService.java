package com.alibou.keycloak.service;

import com.alibou.keycloak.Entity.outcome;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface outcomeService {
    List<outcome> getAllOutcome();
    outcome saveOutcome(outcome outcome);

    Object getOutcome(BigInteger outcomeId);

    void deleteOutcome(BigInteger outcomeId);

    outcome updateOutcome(BigInteger outcomeId, Map<String, String> formData);
}
//