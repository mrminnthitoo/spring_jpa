package com.minnthitoo.spring_jpa.service.impl;

import com.minnthitoo.spring_jpa.common.Mapper;
import com.minnthitoo.spring_jpa.dao.ActorDao;
import com.minnthitoo.spring_jpa.model.dto.ActorDto;
import com.minnthitoo.spring_jpa.model.entity.Actor;
import com.minnthitoo.spring_jpa.service.ActorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    private ActorDao actorDao;

    @Autowired
    private Mapper mapper;


    @Transactional
    @Override
    public Optional<ActorDto> getActorById(Long actorId) {
        Optional<Actor> result = this.actorDao.findById(actorId);
        if (result.isPresent()){
            ActorDto actorDto = this.mapper.map(result.get(), ActorDto.class);
            return Optional.of(actorDto);
        }else {
            return Optional.empty();
        }
    }
}
