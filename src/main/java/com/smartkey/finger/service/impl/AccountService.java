package com.smartkey.finger.service.impl;

import com.smartkey.finger.entity.Account;
import com.smartkey.finger.repository.AccountRepository;
import com.smartkey.finger.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account signup(Account account) {
        Account existingAccount = accountRepository.findByAccountName(account.getAccountName());
        if (existingAccount != null) {
            throw new RuntimeException("Account already exists");
        }
        return accountRepository.save(account);
    }

    @Override
    public Account login(Account account) {
        Account existingAccount = accountRepository.findByAccountName(account.getAccountName());
        if (existingAccount != null && existingAccount.getPassword().equals(account.getPassword())) {
            return existingAccount;
        } else {
            throw new RuntimeException("Invalid username or password");
        }
    }


}
