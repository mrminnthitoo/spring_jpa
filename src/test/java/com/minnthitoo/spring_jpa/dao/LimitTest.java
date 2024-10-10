package com.minnthitoo.spring_jpa.dao;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LimitTest {

    @Autowired
    private ActorDao actorDao;

    @Test
    @Transactional
    public void testLimit() {

    }

}
