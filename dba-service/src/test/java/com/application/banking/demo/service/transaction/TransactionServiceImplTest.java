package com.application.banking.demo.service.transaction;

import com.application.banking.demo.model.transaction.Transaction;
import com.application.banking.demo.model.transaction.TransactionType;
import com.application.banking.demo.persistence.repository.transaction.TransactionRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import javax.swing.text.html.Option;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.application.banking.demo.model.transaction.TransactionType.CREDIT;
import static java.math.BigDecimal.TEN;
import static java.util.Collections.singletonList;
import static java.util.Optional.empty;
import static java.util.UUID.fromString;
import static java.util.UUID.randomUUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    TransactionServiceImpl transactionService;

    @Mock
    TransactionRepository transactionRepository;

    @Test
    void getTransaction_elementPresent_verifyInteraction() {
        when(transactionRepository.findById(uuid())).thenReturn(optionalTransaction());
        transactionService.getTransaction(uuid());
        verify(transactionRepository).findById(uuid());
    }

    @Test
    void getTransaction_elementNotPresent_assertThrowsException() {
        when(transactionRepository.findById(uuid())).thenReturn(empty());
        assertThrows(Exception.class, () -> transactionService.getTransaction(uuid()));
    }

    @Test
    void getTransactions() {
        when(transactionRepository.findAllByAccountId("01")).thenReturn(transactions());
        transactionService.getTransactions("01");
        verify(transactionRepository).findAllByAccountId("01");
    }

    @Test
    void createTransaction() {
        when(transactionRepository.save(transaction())).thenReturn(transaction());
        transactionService.createTransaction(transaction());
        verify(transactionRepository).save(transaction());
    }

    private Transaction transaction() {
        return Transaction.builder()
                .transactionId(uuid())
                .transactionAmount(TEN)
                .transactionType(CREDIT)
                .accountId("01")
                .build();
    }

    private List<Transaction> transactions() {
        return singletonList(
                Transaction.builder()
                        .transactionId(uuid())
                        .transactionAmount(TEN)
                        .transactionType(CREDIT)
                        .accountId("01")
                        .build());
    }

    private Optional<Transaction> optionalTransaction() {
        return Optional.ofNullable(Transaction.builder()
                .transactionId(uuid())
                .transactionAmount(TEN)
                .transactionType(CREDIT)
                .accountId("01")
                .build());
    }

    private UUID uuid() {
        return fromString("00000000-0000-0000-0000-000000000001");
    }
}
