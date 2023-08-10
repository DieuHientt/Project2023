package com.alibou.keycloak.service.Impl;

import com.alibou.keycloak.Entity.saving_target;
import com.alibou.keycloak.repository.saving_targetRepository;
import com.alibou.keycloak.service.saving_targetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.ZoneId;
import java.time.LocalDateTime;
import java.util.Date;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
@Service
public class saving_targetServiceImp implements saving_targetService {

    private saving_targetRepository savingTargetRepository;
    @Autowired
    public saving_targetServiceImp(saving_targetRepository savingTargetRepository) {
        this.savingTargetRepository = savingTargetRepository;
    }

    public List<saving_target> getAllSavingTarget(){
        return savingTargetRepository
                .findAll();
    }

    @Override
    public saving_target saveSavingTarget(saving_target savingTarget) {
        savingTargetRepository.save(savingTarget);
        return savingTarget;
    }

    @Override
    public Object getSavingTarget(BigInteger savingTargetId) {
        return savingTargetRepository
                .findById(savingTargetId)
                .orElse(null);
    }

    @Override
    public void deleteSavingTarget(BigInteger savingTargetId) {
        savingTargetRepository.deleteById(savingTargetId);
    }

    @Override
    public saving_target updateSavingTarget(BigInteger savingTargetId, Map<String, String> formData) {
        saving_target existingSavingTarget = savingTargetRepository.findById(savingTargetId).orElse(null);
        if (existingSavingTarget != null) {
            String name_sv = formData.get("name_sv");
            String describe_sv = formData.get("describe_sv");
            String start_date_str = formData.get("start_date");
            String end_date_str = formData.get("end_date");

            // Chuyển đổi chuỗi ngày thành đối tượng LocalDateTime
            LocalDateTime start_date = LocalDateTime.parse(start_date_str);
            LocalDateTime end_date = LocalDateTime.parse(end_date_str);

            // Chuyển đổi LocalDateTime thành java.util.Date
            Date start_date_util = Date.from(start_date.atZone(ZoneId.systemDefault()).toInstant());
            Date end_date_util = Date.from(end_date.atZone(ZoneId.systemDefault()).toInstant());

            existingSavingTarget.setName_sv(name_sv);
            existingSavingTarget.setDescribe_sv(describe_sv);
            existingSavingTarget.setStart_date(start_date_util);
            existingSavingTarget.setEnd_date(end_date_util);
            String status = formData.get("status");
            existingSavingTarget.setStatus(status);

            return savingTargetRepository.save(existingSavingTarget);
        }
        return null;
    }
}