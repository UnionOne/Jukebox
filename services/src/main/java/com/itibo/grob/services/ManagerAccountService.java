package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Account;
import com.itibo.grob.dataaccess.model.Track;

public interface ManagerAccountService {
    void addAccount(Account account);

    void addTrack(Account account, Track track);

    void deleteAccount(Account account);
}
