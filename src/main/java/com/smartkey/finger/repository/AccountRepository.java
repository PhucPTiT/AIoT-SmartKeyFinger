package com.smartkey.finger.repository;

import com.smartkey.finger.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountName(String accountName);
}
