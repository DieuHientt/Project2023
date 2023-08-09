package com.alibou.keycloak.service;


import com.alibou.keycloak.model.saving_target;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface saving_targetService {

    List<saving_target> getAllSavingTarget();
    saving_target saveSavingTarget(saving_target savingTarget);

    Object getSavingTarget(BigInteger savingTargetId);

    void deleteSavingTarget(BigInteger savingTargetId);

    saving_target updateSavingTarget(BigInteger savingTargetId, Map<String, String> formData);
}
