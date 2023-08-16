package com.TMA.projectJava.service.Impl;


import com.hon.keycloak.entity.report;
import com.hon.keycloak.repository.reportRepository;
import com.hon.keycloak.service.reportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
@Service
public class reportServiceImp implements reportService {

    private reportRepository reportRepository;
    @Autowired
    public reportServiceImp(reportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    public List<report> getAllReport(){
        return reportRepository
                .findAll();
    }

    @Override
    public report saveReport(report report) {
        reportRepository.save(report);
        return report;
    }

    @Override
    public Object getReport(BigInteger reportId) {
        return reportRepository
                .findById(reportId)
                .orElse(null);
    }

    @Override
    public List<report> getReportNotDeleted() {
        return reportRepository.findReportNotDeleted();
    }

    @Override
    public report updateReport(BigInteger reportId, Map<String, String> formData) {
        report existingReport = reportRepository.findById(reportId).orElse(null);
        if (existingReport != null) {
            String name_report = formData.get("name_report");
            String type_report = formData.get("type_report");
            existingReport.setName_report(name_report);
            existingReport.setType_report(type_report);
            String status = formData.get("status");
            existingReport.setStatus(status);

            return reportRepository.save(existingReport);
        }
        return null;
    }
}
