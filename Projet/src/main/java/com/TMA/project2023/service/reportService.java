package com.alibou.keycloak.service;


import com.alibou.keycloak.model.report;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface reportService {

    List<report> getAllReport();
    report saveReport(report report);

    Object getReport(BigInteger reportId);

    void deleteReport(BigInteger reportId);

    report updateReport(BigInteger reportId, Map<String, String> formData);
}
