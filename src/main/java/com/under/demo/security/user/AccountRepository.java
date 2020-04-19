package com.under.demo.security.user;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class AccountRepository {

    private final Map<String, Account> db = new HashMap<>();

    public Optional<Account> findBy(String username) {
        return Optional.ofNullable(db.get(username));
    }

    public void save(Account account) {
        db.put(account.getUsername(), account);
    }
}
