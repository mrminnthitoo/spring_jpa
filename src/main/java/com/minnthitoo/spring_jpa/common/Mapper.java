package com.minnthitoo.spring_jpa.common;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class Mapper {
    private ModelMapper modelMapper = new ModelMapper();

    public Mapper(){
        log.info("Mapper initialized.");
    }

    public <S, T>List<T> mapList(List<S> source, Class<T> targetClass){
        return source
                .stream()
                .map(element -> modelMapper
                        .map(element, targetClass))
                .collect(Collectors.toList());
    }

    public <D> D map(Object source, Class<D> destinationType){
        return this.modelMapper.map(source, destinationType);
    }

    public ModelMapper getModelMapper(){
        return this.modelMapper;
    }

}
