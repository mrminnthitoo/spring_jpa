package com.minnthitoo.spring_jpa.dao;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest
public class BookDaoQueryMethodTest {
    @Autowired
    private BookDao bookDao;

    @Test
    public void test(){
        log.info("Test case Executed");
    }
}
