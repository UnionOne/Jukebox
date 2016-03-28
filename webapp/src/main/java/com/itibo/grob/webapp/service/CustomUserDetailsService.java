package com.itibo.grob.webapp.service;

import com.itibo.grob.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        com.itibo.grob.dataaccess.model.Account domainUser = accountService.findOneAccountByLogin(login);

        return new User(
                domainUser.getLogin(),
                domainUser.getPassword(),
                true, true, true, true,
                getAuthorities(domainUser.getRoles().size())
        );
    }

    public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
        return getGrantedAuthorities(getRoles(role));
    }

    public List<String> getRoles(Integer role) {

        List<String> roles = new ArrayList<>();

        if (role == 1) {
            roles.add("ROLE_USER");
            roles.add("ROLE_ADMIN");
        } else if (role == 2) {
            roles.add("ROLE_USER");
        }
        return roles;
    }

    public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }
}
