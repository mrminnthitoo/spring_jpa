package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.entity.Actor;
import com.minnthitoo.spring_jpa.model.entity.enums.Gender;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.util.*;

@Slf4j
@Rollback(value = false)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ActorRepositoryTest {

    @Autowired
    private ActorRepository actorRepository;

    @Test
    public void insertActor(){
        for (int i = 0; i < 30; i++) {
            Actor actor = new Actor();
            actor.setFirstName("Actor");
            actor.setLastName(String.valueOf(i+5));

            Random random = new Random();

            // birthday
            Date birthday = new GregorianCalendar(random.nextInt(1970, 2020), Calendar.NOVEMBER, 11).getTime();

            actor.setBirthday(birthday);
            actor.setGender(Gender.MALE);
            this.actorRepository.save(actor);
        }

    }

    @Test
    public void getActorByIdTest(){
        Optional<Actor> actor = this.actorRepository.findById(1L);
        log.info("{}", actor.get());
    }

    @Transactional
    @Test
    public void testGetActorWithLimitOffset(){
        //first page -> 10, 0
        List<Actor> actors = this.actorRepository.getActorWithLimitOffset(10, 0);

        //second page -> 10, 11
        // List<Actor> actors = this.actorRepository.getActorWithLimitOffset(10, 11);

        for (Actor actor : actors){
            log.info("{}", actor);
        }
    }

    @Test
    @Transactional
    public void testPaging(){
        Pageable firstPageWithTwoElements = PageRequest.of(0, 2);
        Page<Actor> actors = this.actorRepository.findAll(firstPageWithTwoElements);
        for (Actor actor : actors){
            log.info("{}", actor);
        }
    }

    @Test
    @Transactional
    public void updateActorNameWithActorId(){
        int updatedRow = this.actorRepository.updateActorNameById(2L, "Actor", "2 update");
        log.info("Updated {}", updatedRow);
    }


}
