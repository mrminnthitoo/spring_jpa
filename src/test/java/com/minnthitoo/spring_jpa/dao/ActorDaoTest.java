package com.minnthitoo.spring_jpa.dao;

import com.minnthitoo.spring_jpa.model.entity.Actor;
import com.minnthitoo.spring_jpa.model.entity.Gender;
import com.minnthitoo.spring_jpa.model.entity.Name;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Optional;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback(value = false)
public class ActorDaoTest {
    @Autowired
    private ActorDao actorDao;

    @Test
    void saveActor(){
        for (int i = 0; i < 30; i++){

            Actor actor = new Actor();
            Name name = new Name();
            name.setFirstName("Actor");
            name.setLastName(String.valueOf(i + 5));

            actor.setName(name);
            actor.setGender(Gender.MALE);

//            Date birthday = new GregorianCalendar(1974, Calendar.NOVEMBER, 11).getTime();

            actor.setBirthday(new Date());
            actorDao.save(actor);

        }
    }


    @Test
    void findByIdTest(){
        Optional<Actor> actor = this.actorDao.findById(2L);
        log.info("Actor: {}", actor.get());
    }

    // update actor
    @Test
    @Transactional
    public void updateActorTest(){
        int rowEffected = this.actorDao.updateActorNameById(2L, "Actor", "Updated");
        log.info("Row effected: {}", rowEffected);
    }

    // delete actor
    @Test
    @Transactional
    public void deleteActorByIdTest(){
        int rowEffected = this.actorDao.deleteActorById(2L);
        log.info("Row effected: {}", rowEffected);
    }
}
