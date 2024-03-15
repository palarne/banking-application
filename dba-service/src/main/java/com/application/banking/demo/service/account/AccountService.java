package com.application.banking.demo.service.account;

import com.application.banking.demo.model.account.Account;

public interface AccountService {

    Account getAccount(String accountId);

    void createAccount(Account account);
}
