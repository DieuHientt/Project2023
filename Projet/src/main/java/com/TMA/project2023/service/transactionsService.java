package com.alibou.keycloak.service;

import com.alibou.keycloak.entity.transactions;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public interface transactionsService {

    List<transactions> getAllTransactions();
    transactions saveTransactions(transactions transactions);

    Object getTransactions(BigInteger transactionsId);

    void deleteTransactions(BigInteger transactionsId);

    transactions updateTransactions(BigInteger transactionsId, Map<String, String> formData);
}
