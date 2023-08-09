package com.alibou.keycloak.controller;

import com.alibou.keycloak.model.report;
import com.alibou.keycloak.service.reportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class reportController  {

    @Autowired
    private reportService reportService;


    @GetMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<List<report>> findAllReport() {
        return ResponseEntity.ok(reportService.getAllReport());
    }
    @GetMapping("/{reportId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<report> getReport(@PathVariable BigInteger reportId) {
        return ResponseEntity.ok((report) reportService.getReport(reportId));
    }
    @PostMapping
    @PreAuthorize("hasRole('client_admin')")
    public ResponseEntity<report> saveReport(report report) {
        report savedReport = reportService.saveReport(report);
        return ResponseEntity.ok(savedReport);
    }
    @DeleteMapping("/{cardBrandId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<List<report>> deleteReport(@PathVariable BigInteger reportId) {
        reportService.deleteReport(reportId);
        return ResponseEntity.ok(reportService.getAllReport());
    }
    @PutMapping("/{cardBrandId}")
    @PreAuthorize("hasAnyRole('client_user', 'client_admin')")
    public ResponseEntity<report> updateReport(@PathVariable BigInteger reportId, @RequestParam Map<String, String> formData) {
        report updatedReportResult  = reportService.updateReport(reportId, formData);
        if (updatedReportResult != null) {
            return ResponseEntity.ok(updatedReportResult);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}