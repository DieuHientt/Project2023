package com.TMA.projectJava.service.Impl;

import com.hon.keycloak.entity.saving_target;
import com.hon.keycloak.log.logger;
import com.hon.keycloak.repository.saving_targetRepository;
import com.hon.keycloak.service.saving_targetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public List<saving_target> getSavingTargetNotDeleted() {
        return savingTargetRepository.findSavingTargetNotDeleted();
    }

    @Override
    public saving_target updateSavingTarget(BigInteger savingTargetId, Map<String, String> formData) {
        saving_target existingSavingTarget = savingTargetRepository.findById(savingTargetId).orElse(null);
        if (existingSavingTarget != null) { //Kiểm tra đối tượng có tồn tại
            String dateTimeStr = formData.get("start_date");
            String dateTimeEnd = formData.get("end_date");
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String nameSV = formData.get("name_sv");
                String describeSV = formData.get("describe_sv");
                String amount = formData.get("amount_sv");
                String status = formData.get("status");
                Date startDate = dateFormat.parse(dateTimeStr);
                Date endDate = dateFormat.parse(dateTimeEnd);
                existingSavingTarget.setStart_date(startDate);
                existingSavingTarget.setEnd_date(endDate);
                existingSavingTarget.setName_sv(nameSV);
                existingSavingTarget.setDescribe_sv(describeSV);
                existingSavingTarget.setAmount_sv(Integer.parseInt(amount));
                existingSavingTarget.setStatus(status);
                return savingTargetRepository.save(existingSavingTarget);
            } catch (ParseException e) {
                // Xử lý lỗi khi không thể chuyển đổi chuỗi thành ngày tháng
                logger.error("Can Change String To Date");
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}