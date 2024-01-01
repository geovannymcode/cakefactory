package com.geovannycode.cakefactory.service;

public interface SignupService {
    boolean accountExists(String email);
    void register(String email, String password, String addressLine1, String addressLine2, String postcode);
}
