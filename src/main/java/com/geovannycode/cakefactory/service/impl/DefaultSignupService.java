package com.geovannycode.cakefactory.service.impl;

import com.geovannycode.cakefactory.service.AccountService;
import com.geovannycode.cakefactory.service.AddressService;
import com.geovannycode.cakefactory.service.SignupService;
import javax.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class DefaultSignupService implements SignupService {

    private final AccountService accountService;
    private final AddressService addressService;

    public DefaultSignupService(AccountService accountService, AddressService addressService) {
        this.accountService = accountService;
        this.addressService = addressService;
    }

    @Override
    public boolean accountExists(String email) {
        return false;
    }

    @Override
    @Transactional
    public void register(String email, String password, String addressLine1, String addressLine2, String postcode) {
        this.accountService.register(email, password);
        this.addressService.update(email, addressLine1, addressLine2, postcode);
    }
}
