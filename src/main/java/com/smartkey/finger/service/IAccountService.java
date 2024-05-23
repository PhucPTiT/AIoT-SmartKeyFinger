package com.smartkey.finger.service;

import com.smartkey.finger.entity.Account;

public interface IAccountService {
    Account signup(Account account);

    Account login(Account account);
}
