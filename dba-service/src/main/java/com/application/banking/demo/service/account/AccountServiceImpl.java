package com.application.banking.demo.service.account;

import com.application.banking.demo.model.account.Account;
import com.application.banking.demo.persistence.repository.account.AccountRepository;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;

    @Override
    @SneakyThrows
    public Account getAccount(String accountId) {
        val account = accountRepository.findById(accountId);
        if (!account.isPresent())
            throw new Exception(format("account %s not found", accountId));
        return account.get();
    }

    @Override
    public void createAccount(Account account) {
        accountRepository.save(account);
    }
}
