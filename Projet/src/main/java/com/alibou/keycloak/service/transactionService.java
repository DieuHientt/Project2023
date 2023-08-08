package com.alibou.keycloak.service;


import com.hon.keycloak.model.transactions;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface transactionService {
    List<transactions> getAllTransaction();
    transactions saveTransaction(transactions transactions);

    Object getTransaction(BigInteger transactionId);

    void deleteTransaction(BigInteger transactionId);

    transactions updateTransaction(BigInteger transactionId, Map<String, String> formData);
}
//