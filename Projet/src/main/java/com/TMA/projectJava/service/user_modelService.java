package com.alibou.keycloak.service;

import com.alibou.keycloak.Entity.user_model;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface user_modelService {

    List<user_model> getAllUserModel();
    user_model saveUserModel(user_model userModel);

    Object getUserModel(BigInteger userModelId);

    void deleteUserModel(BigInteger userModelId);

    user_model updateUserModel(BigInteger userModelId, Map<String, String> formData);
}
