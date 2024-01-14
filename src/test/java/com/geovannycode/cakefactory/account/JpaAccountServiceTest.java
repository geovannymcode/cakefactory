package com.geovannycode.cakefactory.account;

import com.geovannycode.cakefactory.entity.Account;
import com.geovannycode.cakefactory.entity.AccountEntity;
import com.geovannycode.cakefactory.repository.AccountRepository;
import com.geovannycode.cakefactory.service.AccountService;
import com.geovannycode.cakefactory.service.impl.JpaAccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class JpaAccountServiceTest {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TestEntityManager testEntityManager;

    private AccountService accountService;

    @BeforeEach
    void setUp() {
        accountService = new JpaAccountService(accountRepository, new BCryptPasswordEncoder());
    }

    @Test
    void persistUserRecordOnRegister() {
        String email = "test@example.com";
        accountService.register(email, "test password");

        AccountEntity accountEntity = testEntityManager.find(AccountEntity.class, email);
        assertThat(accountEntity).isNotNull();
        assertThat(accountEntity.getEmail()).isEqualTo(email);
    }

    @Test
    void encryptsPassword() {
        String email = "test@example.com";
        String password = "test password";
        accountService.register(email, password);

        AccountEntity accountEntity = testEntityManager.find(AccountEntity.class, email);
        assertThat(accountEntity.getPassword()).isNotBlank();
        assertThat(accountEntity.getPassword()).isNotEqualTo(password);
    }

    @Test
    void findsAccountByEmail() {
        String email = "test@example.com";
        saveTestAccount(email);

        Account account = accountService.find(email);
        assertThat(account).isNotNull();
    }

    @Test
    void checksForExistence() {
        String email = "test@example.com";
        String anotherEmail = "test2@example.com";
        saveTestAccount(email);

        assertThat(accountService.exists(email)).isTrue();
        assertThat(accountService.exists(anotherEmail)).isFalse();
    }

    private void saveTestAccount(String email) {
        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setEmail(email);
        accountEntity.setPassword("test");

        testEntityManager.persist(accountEntity);
    }


}
