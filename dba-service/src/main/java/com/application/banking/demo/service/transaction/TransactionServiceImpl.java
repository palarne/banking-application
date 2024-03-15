package com.application.banking.demo.service.transaction;

import com.application.banking.demo.model.transaction.Transaction;
import com.application.banking.demo.persistence.repository.transaction.TransactionRepository;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    @SneakyThrows
    public Transaction getTransaction(UUID transactionId) {
        val transaction = transactionRepository.findById(transactionId);
        if (!transaction.isPresent())
            throw new Exception(String.format("transaction %s not found", transactionId));
        return transaction.get();
    }

    @Override
    public List<Transaction> getTransactions(String accountId) {
        return transactionRepository.findAllByAccountId(accountId);
    }

    @Override
    public void createTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }
}
