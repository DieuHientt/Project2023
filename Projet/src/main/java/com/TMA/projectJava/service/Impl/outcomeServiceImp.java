package com.alibou.keycloak.service.Impl;

import com.alibou.keycloak.Entity.outcome;
import com.alibou.keycloak.repository.outcomeRepository;
import com.alibou.keycloak.service.outcomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.util.Date;
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
            String dateTimeStr = formData.get("date_time");

            // Chuyển đổi chuỗi ngày thành đối tượng LocalDateTime
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr);

            // Chuyển đổi LocalDateTime thành java.util.Date
            Date date = Date.from(dateTime.atZone(ZoneId.systemDefault()).toInstant());

            existingOutcome.setDate_time(date);
            String status = formData.get("status");
            existingOutcome.setStatus(status);
        }
        return null;
    }
}
//