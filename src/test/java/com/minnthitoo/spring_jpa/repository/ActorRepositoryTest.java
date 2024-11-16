package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.entity.Actor;
import com.minnthitoo.spring_jpa.model.entity.enums.Gender;
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
public class ActorRepositoryTest {

    @Autowired
    private ActorRepository actorRepository;

    @Test
    public void insertActor(){
        Actor actor = new Actor();
        actor.setFirstName("Leonardo");
        actor.setLastName("Dicaprio");

        // birthday
        Date birthday = new GregorianCalendar(1974, Calendar.NOVEMBER, 11).getTime();

        actor.setBirthday(birthday);
        actor.setGender(Gender.MALE);
        this.actorRepository.save(actor);
    }

    @Test
    public void getActorByIdTest(){
        Optional<Actor> actor = this.actorRepository.findById(1L);
        log.info("{}", actor.get());
    }

}
