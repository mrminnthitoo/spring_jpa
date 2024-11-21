package com.minnthitoo.spring_jpa.service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@Slf4j
@Rollback(value = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MoneyTransferServiceTest {

    @Autowired
    private MoneyTransferService moneyTransferService;

    @Test
    @Transactional
    public void testTransfer(){
        this.moneyTransferService.sendMoney(1L, 2L, 1000D);
    }

}
