package com.geovannycode.cakefactory.service;

import com.geovannycode.cakefactory.entity.Account;

public interface AccountService {
    void register(String email, String password);
    Account find(String email);
    boolean exists(String email);
}
