package com.alibou.keycloak.service.Impl;

import com.alibou.keycloak.model.saving_target;
import com.alibou.keycloak.repository.saving_targetRepository;
import com.alibou.keycloak.service.saving_targetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.math.BigInteger;
import java.util.Date;
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
            String start_date = formData.get("start_date");
            String end_date = formData.get("end_date");

           
            existingSavingTarget.setName_sv(name_sv);
            existingSavingTarget.setDescribe_sv(describe_sv);
          //  existingSavingTarget.setStart_date(Date.parse(Long.parseLong(start_date)));
          //  existingSavingTarget.setEnd_date(Date.parse(end_date));


            return savingTargetRepository.save(existingSavingTarget);
        }
        return null;
    }
}