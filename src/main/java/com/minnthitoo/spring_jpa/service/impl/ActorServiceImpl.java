package com.minnthitoo.spring_jpa.service.impl;

import com.minnthitoo.spring_jpa.common.Mapper;
import com.minnthitoo.spring_jpa.model.dto.ActorDto;
import com.minnthitoo.spring_jpa.model.entity.Actor;
import com.minnthitoo.spring_jpa.repository.ActorRepository;
import com.minnthitoo.spring_jpa.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private Mapper mapper;

    @Override
    public Optional<ActorDto> getActorById(Long actorId) {
        Optional<Actor> actor = this.actorRepository.findById(actorId);
        if (actor.isPresent()){
            ActorDto actorDto = this.mapper.map(actor.get(), ActorDto.class);
            return Optional.of(actorDto);
        }else {
            return Optional.empty();
        }
    }
}
