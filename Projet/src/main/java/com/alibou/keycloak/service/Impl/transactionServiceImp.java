package com.alibou.keycloak.service.Impl;

import com.hon.keycloak.model.transactions;
import com.hon.keycloak.repository.transactionRepository;
import com.hon.keycloak.service.transactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
@Service
public class transactionServiceImp implements transactionService {
    private final transactionRepository transactionRepository;
    @Autowired
    public transactionServiceImp(transactionRepository transactionRepository) {
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
        if (existingTransaction != null) { //Kiểm tra đối tượng có tồn tại
            String amount = formData.get("amount");
            String note = formData.get("note");
            existingTransaction.setAmount(Integer.parseInt(amount));
            existingTransaction.setNote(note);
            return transactionRepository.save(existingTransaction);
        }
        return null;
    }
}
//