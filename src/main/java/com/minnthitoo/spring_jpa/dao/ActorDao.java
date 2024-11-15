package com.minnthitoo.spring_jpa.dao;

import com.minnthitoo.spring_jpa.model.entity.Actor;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorDao extends PagingAndSortingRepository<Actor, Long>, CrudRepository<Actor, Long> {

    /*
    @Query("select extract(year from birthday) from Actor")
    List<Integer> getYears();

    @Query("select a from Actor a order by a.id limit ?1 offset ?2")
    List<Actor> getActorWithLimitOffset(Integer limit, Integer offset);

    @Modifying
    @Transactional
    @Query("update Actor actor set actor.name.firstName = ?2, actor.name.lastName = ?3 where actor.id = ?1")
    int updateActorNameById(Long id, String firstName, String lastName);

    @Modifying
    @Transactional
    @Query("delete Actor actor where actor.id = ?1")
    int deleteActorById(Long id);
     */

}
