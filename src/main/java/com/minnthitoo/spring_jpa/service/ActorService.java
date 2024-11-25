package com.minnthitoo.spring_jpa.service;

import com.minnthitoo.spring_jpa.model.dto.ActorDto;

import java.util.Optional;

public interface ActorService {
    Optional<ActorDto> getActorById(Long actorId);
}
