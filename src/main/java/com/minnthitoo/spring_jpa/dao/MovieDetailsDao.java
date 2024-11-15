package com.minnthitoo.spring_jpa.dao;

import com.minnthitoo.spring_jpa.model.entity.MovieDetails;
import org.springframework.data.repository.CrudRepository;

public interface MovieDetailsDao extends CrudRepository<MovieDetails, Integer> {
}
