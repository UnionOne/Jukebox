package com.itibo.grob.webapp.bean;

import com.itibo.grob.dataaccess.model.Account;
import com.itibo.grob.services.AccountService;
import com.itibo.grob.services.ManagerAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean(name = "accountBean")
@SessionScoped
@Component
public class AccountBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    AccountService accountService;

    @Autowired
    ManagerAccountService managerAccountService;

    private String login;
    private String email;
    private String firstName;
    private String lastName;
    private List<Account> accountList;

    public AccountBean() {
    }

    @PostConstruct
    private void init() {
        accountList = accountService.findAll();
    }

    public void editAccount(Account account) {
        account.setEdit(true);
        this.login = account.getLogin();
        this.email = account.getEmail();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        accountService.save(account);
    }

    public void saveAccount(Account account) {
        account.setEdit(false);
        account.setLogin(this.login);
        account.setEmail(this.email);
        account.setFirstName(this.firstName);
        account.setLastName(this.lastName);
        accountService.saveAndFlush(account);
    }

    public void deleteAccount(Account account) {
        managerAccountService.deleteAccount(account);
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

    public List<Account> getAccountList() {
        accountList = accountService.findAll();
        return accountList;
    }

    public void setAccountList(List<Account> accountList) {
        this.accountList = accountList;
    }
}
