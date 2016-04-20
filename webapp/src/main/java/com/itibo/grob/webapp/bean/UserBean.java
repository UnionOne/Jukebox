package com.itibo.grob.webapp.bean;

import com.itibo.grob.dataaccess.model.Account;
import com.itibo.grob.dataaccess.model.Jukebox;
import com.itibo.grob.services.AccountService;
import com.itibo.grob.services.JukeboxService;
import com.itibo.grob.services.ManagerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "userBean")
@SessionScoped
@Component
public class UserBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private AccountService accountService;

    @Autowired
    private JukeboxService jukeboxService;

    @Autowired
    private ManagerAccountService managerAccountService;

    private String login;
    private String password;
    private String email;
    private String firstName;
    private String lastName;

    public void addUser() {
        if (!login.isEmpty() && !email.isEmpty() && !password.isEmpty() && !firstName.isEmpty() && !lastName.isEmpty()) {
            Account newAccount = new Account(this.login, this.email, this.password, this.firstName, this.lastName);
            managerAccountService.addAccount(newAccount);
        }
    }

    public void reset() {
        this.login = "";
        this.password = "";
        this.email = "";
        this.firstName = "";
        this.lastName = "";
    }

    private String getAuthUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

    public Account getAuthAccount() {
        return accountService.findOneAccountByLogin(getAuthUsername());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
