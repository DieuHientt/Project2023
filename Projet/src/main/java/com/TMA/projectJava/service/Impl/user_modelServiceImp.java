package com.alibou.keycloak.service.Impl;

import com.alibou.keycloak.Entity.user_model;
import com.alibou.keycloak.repository.user_modelRepository;
import com.alibou.keycloak.service.user_modelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public class user_modelServiceImp implements user_modelService {
    private  user_modelRepository userModelRepository;
    @Autowired
    public user_modelServiceImp(user_modelRepository userModelRepository) {
        this.userModelRepository = userModelRepository;
    }

    public List<user_model> getAllUserModel(){
        return userModelRepository
                .findAll();
    }

    @Override
    public user_model saveUserModel(user_model userModel) {
        userModelRepository.save(userModel);
        return userModel;
    }

    @Override
    public Object getUserModel(BigInteger userModelId) {
        return userModelRepository
                .findById(userModelId)
                .orElse(null);
    }

    @Override
    public void deleteUserModel(BigInteger userModelId) {
        userModelRepository.deleteById(userModelId);
    }

    @Override
    public user_model updateUserModel(BigInteger UserModelId, Map<String, String> formData) {
        user_model existingUserModel = userModelRepository.findById(UserModelId).orElse(null);
        if (existingUserModel != null) {
            String email = formData.get("email");
            String email_verified = formData.get("email_verified");
            String first_name = formData.get("first_name");
            String last_name = formData.get("last_name");
            String enabled = formData.get("enabled");

            existingUserModel.setEmail(email);
            existingUserModel.setEmail_verified(email_verified);
            existingUserModel.setEnabled(enabled);
            existingUserModel.setFirst_name(first_name);
            existingUserModel.setFirst_name(last_name);
            String status = formData.get("status");
            existingUserModel.setStatus(status);

            return userModelRepository.save(existingUserModel);
        }
        return null;
    }
}
