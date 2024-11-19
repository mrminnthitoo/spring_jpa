package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorRepository extends JpaRepository<Actor, Long> {

    @Query("select actor from Actor actor order by id limit ?1 offset ?2")
    List<Actor> getActorWithLimitOffset(Integer limit, Integer offset);

}
