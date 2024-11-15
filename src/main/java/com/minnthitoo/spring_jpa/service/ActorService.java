package com.minnthitoo.spring_jpa.service;

import com.minnthitoo.spring_jpa.model.dto.ActorDto;
import com.minnthitoo.spring_jpa.model.entity.Actor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ActorService{

    Optional<ActorDto> getActorById(Long actorId);

}
