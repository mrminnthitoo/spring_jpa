package com.minnthitoo.spring_jpa.dao;

import com.minnthitoo.spring_jpa.model.entity.Actor;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LimitTest {

    @Autowired
    private ActorDao actorDao;

    /*
    @Test
    @Transactional
    public void getActorWithLimitOffsetTest() {
        List<Actor> actors = actorDao.getActorWithLimitOffset(0, 20);
        for (Actor actor : actors) {
            log.info("{}", actor);
        }
    }

     */

    @Test
    @Transactional
    public void testPaging(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Actor> actors = actorDao.findAll(pageable);
        List<Actor> actorList = actors.getContent();
        for(Actor actor : actorList){
            log.info("{}", actor);
        }
    }

}
