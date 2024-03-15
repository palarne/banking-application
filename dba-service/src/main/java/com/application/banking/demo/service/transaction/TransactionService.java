package com.application.banking.demo.service.transaction;

import com.application.banking.demo.model.transaction.Transaction;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    Transaction getTransaction(UUID transactionId);

    List<Transaction> getTransactions(String accountId);

    void createTransaction(Transaction transaction);
}
