package com.alibou.keycloak.service;



import com.alibou.keycloak.Entity.transactions;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface transactionsService {
    List<transactions> getAllTransaction();
    transactions saveTransaction(transactions transactions);

    Object getTransaction(BigInteger transactionId);

    void deleteTransaction(BigInteger transactionId);

    transactions updateTransaction(BigInteger transactionId, Map<String, String> formData);
}
//