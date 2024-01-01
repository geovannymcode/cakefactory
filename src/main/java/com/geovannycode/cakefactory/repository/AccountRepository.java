package com.geovannycode.cakefactory.repository;

import com.geovannycode.cakefactory.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<AccountEntity, String> {
    AccountEntity findByEmail(String email);
}
