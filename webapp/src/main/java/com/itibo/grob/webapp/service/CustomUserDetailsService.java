package com.itibo.grob.webapp.service;

import com.itibo.grob.dataaccess.model.Account;
import com.itibo.grob.dataaccess.model.Role;
import com.itibo.grob.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Account account = accountService.findOneAccountByLogin(login);

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Role role : account.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(account.getLogin(), account.getPassword(), true, true, true, true, authorities);
    }
}
