package com.geovannycode.cakefactory.repository;

import com.geovannycode.cakefactory.entity.AddressEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<AddressEntity, String> {
}
