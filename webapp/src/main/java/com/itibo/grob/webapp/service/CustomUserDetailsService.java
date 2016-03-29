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

//        if (account.getRoles().size() == 1) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//        } else if (account.getRoles().size() == 2) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        }

        for (Role role : account.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }

        return new User(account.getLogin(), account.getPassword(), true, true, true, true, authorities);
    }

//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        Account account = accountService.findOneAccountByLogin(login);
//
//        return new User(
//                account.getLogin(),
//                account.getPassword(),
//                true, true, true, true,
//                getAuthorities(account.getRoles().size())
//        );
//    }
//
//    private Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
//        return getGrantedAuthorities(getRoles(role));
//    }
//
//    private List<String> getRoles(Integer role) {
//
//        List<String> roles = new ArrayList<>();
//
//        if (role == 2) {
//            roles.add("ROLE_USER");
//            roles.add("ROLE_ADMIN");
//        } else if (role == 1) {
//            roles.add("ROLE_USER");
//        }
//        return roles;
//    }
//
//    private static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
//        List<GrantedAuthority> authorities = new ArrayList<>();
//
//        for (String role : roles) {
//            authorities.add(new SimpleGrantedAuthority(role));
//        }
//        return authorities;
//    }
}
