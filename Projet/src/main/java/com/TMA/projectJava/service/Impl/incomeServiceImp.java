package com.alibou.keycloak.service.Impl;

import com.alibou.keycloak.Entity.income;
import com.alibou.keycloak.repository.incomeRepository;
import com.alibou.keycloak.service.incomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.time.ZoneId;
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
            String dateTimeStr = formData.get("date_time");

            // Chuyển đổi chuỗi ngày thành đối tượng LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);

            // Chuyển đổi LocalDateTime thành java.util.Date
            Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

            existingIncome.setDate_time(date);
            String status = formData.get("status");
            existingIncome.setStatus(status);
        }
        return null;
    }
}
