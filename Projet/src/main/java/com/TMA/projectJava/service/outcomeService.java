package com.TMA.projectJava.service;


import com.hon.keycloak.entity.outcome;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface outcomeService {
    List<outcome> getAllOutcome();
    outcome saveOutcome(outcome outcome);

    Object getOutcome(BigInteger outcomeId);

    List<outcome> getOutcomeNotDeleted();

    outcome updateOutcome(BigInteger outcomeId, Map<String, String> formData);
}
//