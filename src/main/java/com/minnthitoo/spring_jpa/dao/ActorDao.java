package com.minnthitoo.spring_jpa.dao;

import com.minnthitoo.spring_jpa.model.entity.Actor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActorDao extends CrudRepository<Actor, Long> {

    @Query("select extract(year from birthday) from Actor")
    List<Integer> getYears();

}
