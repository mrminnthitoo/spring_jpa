package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.entity.BankAccount;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@Rollback(value = false)
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BankAccountRepositoryTest {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Test
    public void testCreateBankAccount(){
        BankAccount accountA = new BankAccount();
        accountA.setAccountName("Account A");
        accountA.setBalance(500D);

        this.bankAccountRepository.save(accountA);

        BankAccount accountB = new BankAccount();
        accountB.setAccountName("Account B");
        accountB.setBalance(500D);

        this.bankAccountRepository.save(accountB);

    }


}
