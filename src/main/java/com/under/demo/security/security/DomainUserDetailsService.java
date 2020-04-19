package com.under.demo.security.security;

import com.under.demo.security.user.Account;
import com.under.demo.security.user.AccountRepository;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class DomainUserDetailsService implements UserDetailsService {

    private final AccountRepository userRepository;

    public DomainUserDetailsService(AccountRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account appUser = userRepository.findBy(username)
                .orElseThrow(() -> new AuthenticationServiceException("username " + username + " not found"));

        return User.builder()
                .username(username)
                .password(appUser.getPassword())
                .roles(appUser.getRoles().toArray(new String[0]))
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
