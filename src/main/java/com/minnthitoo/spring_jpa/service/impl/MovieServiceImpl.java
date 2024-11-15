package com.minnthitoo.spring_jpa.service.impl;

import com.minnthitoo.spring_jpa.common.Mapper;
import com.minnthitoo.spring_jpa.dao.ActorDao;
import com.minnthitoo.spring_jpa.dao.MovieDao;
import com.minnthitoo.spring_jpa.model.dto.MovieDto;
import com.minnthitoo.spring_jpa.model.entity.Actor;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import com.minnthitoo.spring_jpa.model.entity.MovieDetails;
import com.minnthitoo.spring_jpa.service.MovieService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private ActorDao actorDao;

    @Autowired
    private Mapper mapper;

    @Transactional
    @Override
    public List<MovieDto> getAllMovies() {
        List<MovieDto> moviesDtoList = new ArrayList<>();
        List<Movie> movies = (List<Movie>) this.movieDao.findAll();
        moviesDtoList = mapper.mapList(movies, MovieDto.class);
        return moviesDtoList;
    }

    @Transactional
    @Override
    public List<MovieDto> getAllMoviesByTitle(String title) {
        List<Movie> movieEntityResult = this.movieDao.findByTitleNative(title);
        return this.mapper.mapList(movieEntityResult, MovieDto.class);
    }

    @Transactional
    @Override
    public Optional<MovieDto> getMovieById(Long id) {
        Optional<Movie> result = this.movieDao.findById(id);
        if (result.isPresent()){
            MovieDto movieDto = this.mapper.map(result.get(), MovieDto.class);
            return Optional.of(movieDto);
        }else {
            return Optional.empty();
        }

    }

    @Transactional
    @Override
    public MovieDto saveMovie(MovieDto movieDto) {
        Movie movie = this.mapper.map(movieDto, Movie.class);
        MovieDetails movieDetails = movie.getMovieDetails();
        movie.setMovieDetails(movieDetails);
        movieDetails.setMovie(movie);
        this.movieDao.save(movie);
        return this.mapper.map(movie, MovieDto.class);
    }

    @Transactional
    @Override
    public MovieDto updateMovie(MovieDto movieDto) {
        Movie movieToBeUpdate = this.mapper.map(movieDto, Movie.class);
        Optional<Movie> movieEntityResult = this.movieDao.findById(movieToBeUpdate.getId());
        Movie movie = movieEntityResult.get();
        movie.setTitle(movieToBeUpdate.getTitle());
        movie.setYear(movieToBeUpdate.getYear());
        movie.setGenre(movieToBeUpdate.getGenre());
        movie.getMovieDetails().setDetails(movieToBeUpdate.getMovieDetails().getDetails());
        this.movieDao.save(movie);
        return this.mapper.map(movie, MovieDto.class);
    }

    @Transactional
    @Override
    public MovieDto deleteMovieById(Long movieId) {
        Optional<Movie> movieEntityResult = this.movieDao.findById(movieId);
        this.movieDao.delete(movieEntityResult.get());
        MovieDto dto = this.mapper.map(movieEntityResult.get(), MovieDto.class);

        return dto;
    }

    @Transactional
    @Override
    public MovieDto assignActorToMovie(Long movieId, Long actorId) {
        Optional<Movie> movieEntityResult = this.movieDao.findById(movieId);
        Movie movie = movieEntityResult.get();

        Optional<Actor> actorEntityResult = this.actorDao.findById(actorId);
        Actor actor = actorEntityResult.get();

        movie.getActors().add(actor);
        actor.getMovies().add(movie);
        this.movieDao.save(movie);
        return this.mapper.map(movie, MovieDto.class);
    }

}
