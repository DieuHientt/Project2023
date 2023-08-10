package com.alibou.keycloak.service.Impl;

import com.alibou.keycloak.Entity.transactions;
import com.alibou.keycloak.repository.transactionRepository;
import com.alibou.keycloak.service.transactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
@Service
public class transactionsServiceImp implements transactionsService {
    private transactionRepository transactionRepository;
    @Autowired
    public transactionsServiceImp(transactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    public List<transactions> getAllTransaction(){
        return transactionRepository
                .findAll();
    }

    @Override
    public transactions saveTransaction(transactions transactions) {
        transactionRepository.save(transactions);
        return transactions;
    }

    @Override
    public Object getTransaction(BigInteger transactionId) {
        return transactionRepository
                .findById(transactionId)
                .orElse(null);
    }

    @Override
    public void deleteTransaction(BigInteger transactionId) {
        transactionRepository.deleteById(transactionId);
    }
    @Override
    public transactions updateTransaction(BigInteger transactionId, Map<String, String> formData) {
        transactions existingTransaction = transactionRepository.findById(transactionId).orElse(null);
        if (existingTransaction != null) {
            String amount = formData.get("amount");
            String note = formData.get("note");
            existingTransaction.setAmount(Integer.parseInt(amount));
            existingTransaction.setNote(note);
            String status = formData.get("status");
            existingTransaction.setStatus(status);
            return transactionRepository.save(existingTransaction);
        }
        return null;
    }
}
