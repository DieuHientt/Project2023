package com.TMA.projectJava.service;


import com.hon.keycloak.entity.report;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface reportService {

    List<report> getAllReport();
    report saveReport(report report);

    Object getReport(BigInteger reportId);

    List<report> getReportNotDeleted();

    report updateReport(BigInteger reportId, Map<String, String> formData);
}
