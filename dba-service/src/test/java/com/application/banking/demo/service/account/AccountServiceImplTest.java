package com.application.banking.demo.service.account;

import com.application.banking.demo.model.account.Account;
import com.application.banking.demo.persistence.repository.account.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static java.math.BigDecimal.TEN;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @InjectMocks
    AccountServiceImpl accountService;

    @Mock
    AccountRepository accountRepository;

    @Test
    void getAccount_elementPresent_verifyInteraction() {
        when(accountRepository.findById("01")).thenReturn(optionalAccount());
        accountService.getAccount("01");
        verify(accountRepository).findById("01");
    }

    @Test
    void getAccount_elementNotPresent_assertThrowsException() {
        when(accountRepository.findById("01")).thenReturn(empty());
        assertThrows(Exception.class, () -> accountService.getAccount("01"));
    }

    @Test
    void createAccount_elementPresent_verifyInteraction() {
        when(accountRepository.save(account())).thenReturn(account());
        accountService.createAccount(account());
        verify(accountRepository).save(account());
    }

    private Account account() {
        return Account.builder()
                .accountId("01")
                .accountBalance(TEN)
                .build();
    }

    private Optional<Account> optionalAccount() {
        return ofNullable(Account.builder()
                .accountId("01")
                .accountBalance(TEN)
                .build());
    }
}
