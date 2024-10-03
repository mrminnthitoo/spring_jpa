package com.minnthitoo.spring_jpa.dao;

import com.minnthitoo.spring_jpa.model.entity.Actor;
import com.minnthitoo.spring_jpa.model.entity.Gender;
import com.minnthitoo.spring_jpa.model.entity.Name;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActorDaoTest {
    @Autowired
    private ActorDao actorDao;


    /*
    @Test
    void saveActor(){
        Actor actor = new Actor();
        Name name = new Name();
        name.setFirstName("Leonardo");
        name.setLastName("Dicaprio");

        actor.setName(name);
        actor.setGender(Gender.MALE);

        Date birthday = new GregorianCalendar(1974, Calendar.NOVEMBER, 11).getTime();

        actor.setBirthday(birthday);
        actorDao.save(actor);
    }

     */


    @Test
    void findByIdTest(){
        Optional<Actor> actor = this.actorDao.findById(2L);
        log.info("Actor: {}", actor.get());
    }
}
