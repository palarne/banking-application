package com.application.banking.demo.persistence.repository.transaction;

import com.application.banking.demo.model.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findAllByAccountId(String accountId);
}
