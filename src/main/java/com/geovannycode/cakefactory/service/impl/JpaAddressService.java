package com.geovannycode.cakefactory.service.impl;

import com.geovannycode.cakefactory.entity.Address;
import com.geovannycode.cakefactory.entity.AddressEntity;
import com.geovannycode.cakefactory.repository.AddressRepository;
import com.geovannycode.cakefactory.service.AddressService;
import org.springframework.stereotype.Component;

@Component
public class JpaAddressService implements AddressService {

    private static final Address EMPTY_ADDRESS = new Address("", "", "");

    private final AddressRepository addressRepository;

    public JpaAddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Address findOrEmpty(String email) {
        return this.addressRepository
                .findById(email)
                .map(entity -> new Address(entity.getAddressLine1(), entity.getAddressLine2(), entity.getPostcode()))
                .orElse(EMPTY_ADDRESS);
    }

    @Override
    public void update(String email, String addressLine1, String addressLine2, String postcode) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setEmail(email);
        addressEntity.setAddressLine1(addressLine1);
        addressEntity.setAddressLine2(addressLine2);
        addressEntity.setPostcode(postcode);
        this.addressRepository.save(addressEntity);
    }
}
