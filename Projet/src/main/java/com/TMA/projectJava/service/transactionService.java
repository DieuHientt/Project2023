package com.TMA.projectJava.service;


import com.hon.keycloak.entity.transactions;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface transactionService {
    List<transactions> getAllTransaction();
    transactions saveTransaction(transactions transactions);

    Object getTransaction(BigInteger transactionId);

    List<transactions> getTransactionNotDeleted();

    transactions updateTransaction(BigInteger transactionId, Map<String, String> formData);

}
//