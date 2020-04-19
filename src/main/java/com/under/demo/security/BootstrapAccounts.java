package com.under.demo.security;

import com.under.demo.security.user.Account;
import com.under.demo.security.user.AccountRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class BootstrapAccounts {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    BootstrapAccounts(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @EventListener
    void onStartup(ApplicationReadyEvent event) {
        List.of(
                new Account().setUsername("under").setPassword(passwordEncoder.encode("test")).setRoles(List.of("USER")),
                new Account().setUsername("admin").setPassword(passwordEncoder.encode("admintest")).setRoles(List.of("USER", "ADMIN"))
        ).forEach(accountRepository::save);
    }
}
