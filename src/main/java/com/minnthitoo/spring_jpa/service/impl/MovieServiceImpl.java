package com.minnthitoo.spring_jpa.service.impl;

import com.minnthitoo.spring_jpa.common.Mapper;
import com.minnthitoo.spring_jpa.common.response.exception.NotFoundException;
import com.minnthitoo.spring_jpa.model.dto.MovieDto;
import com.minnthitoo.spring_jpa.model.entity.Actor;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import com.minnthitoo.spring_jpa.model.entity.MovieDetails;
import com.minnthitoo.spring_jpa.repository.ActorRepository;
import com.minnthitoo.spring_jpa.repository.MovieRepository;
import com.minnthitoo.spring_jpa.service.MovieService;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private Mapper mapper;

    @Transactional
    @Override
    public List<MovieDto> getAllMovies() {
        List<Movie> movies = this.movieRepository.findAll();
        return this.mapper.mapList(movies, MovieDto.class);
    }

    @Transactional
    @Override
    public List<MovieDto> getMoviesByTitle(String title) {
        List<Movie> movies = this.movieRepository.getAllMovieLike(title);
        return this.mapper.mapList(movies, MovieDto.class);
    }

    @Transactional
    @Override
    public Optional<MovieDto> getMovieById(Long movieId) {
        Optional<Movie> result = this.movieRepository.findById(movieId);
        return result.map(movie -> this.mapper.map(movie, MovieDto.class));
    }

    @Transactional
    @Override
    public MovieDto createMovie(MovieDto movieDto) {
        Movie movieToSave = this.mapper.map(movieDto, Movie.class);

        // add movie to MovieDetails (Bi-Directional)
        MovieDetails movieDetails = movieToSave.getMovieDetails();
        movieDetails.setMovie(movieToSave);

        Movie savedMovie = this.movieRepository.save(movieToSave);
        return this.mapper.map(savedMovie, MovieDto.class);
    }

    @Transactional
    @Override
    public MovieDto updateMovie(MovieDto movieDto) throws NotFoundException {
        Movie movieToUpdate = this.mapper.map(movieDto, Movie.class);

        Optional<Movie> movieEntityResult = this.movieRepository.findById(movieToUpdate.getId());
        if (movieEntityResult.isPresent()){
            Movie movie = movieEntityResult.get();
            movie.setTitle(movieToUpdate.getTitle());
            movie.setYear(movieToUpdate.getYear());
            movie.setGenre(movieToUpdate.getGenre());

            MovieDetails movieDetails = new MovieDetails();
            movieDetails.setDetails(movieToUpdate.getMovieDetails().getDetails());
            movieDetails.setMovie(movie);

            movie.getMovieDetails().setMovie(movie);

            Movie updatedMovie = this.movieRepository.save(movie);
            return this.mapper.map(updatedMovie, MovieDto.class);

        }else {
            Map<String, String> error = new HashMap<>();
            error.put("movieId", "Movie Id " + movieToUpdate.getId() + " not found.");
            throw new NotFoundException("Movie Not found.", error);
        }
    }

    @Transactional
    @Override
    public MovieDto deleteMovie(MovieDto movieDto) throws NotFoundException {
        Optional<Movie> movieToDelete = this.movieRepository.findById(movieDto.getId());
        if (movieToDelete.isPresent()){
            this.movieRepository.delete(movieToDelete.get());
            return this.mapper.map(movieToDelete.get(), MovieDto.class);
        }else {
            Map<String, String> error = new HashMap<>();
            error.put("movieId", "Movie id " + movieDto.getId() + " not found.");
            throw new NotFoundException("Movie not found.", error);
        }
    }

    @Transactional
    @Override
    public MovieDto assignActorToMovie(Long movieId, Long actorId) throws NotFoundException {
        Optional<Movie> movieResult = this.movieRepository.findById(movieId);
        Optional<Actor> actorResult = this.actorRepository.findById(actorId);

        if (movieResult.isPresent() && actorResult.isPresent()){
            Movie movie = movieResult.get();
            Actor actor = actorResult.get();

            movie.getActors().add(actor);
            actor.getMovies().add(movie);

            Movie linkedMovie = this.movieRepository.save(movie);
            return this.mapper.map(linkedMovie, MovieDto.class);

        }else {
            Map<String, String> error = new HashMap<>();
            if (movieResult.isEmpty()){
                error.put("movieId", "Movie id " + movieId + " not found.");
            }
            if (actorResult.isEmpty()){
                error.put("actorId", "Actor id " + actorId + " not found.");
            }
            throw new NotFoundException("Actor Not found.", error);
        }

    }
}
