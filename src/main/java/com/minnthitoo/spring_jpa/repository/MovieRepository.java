package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.dto.TitleAndGenre;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {

    @Query(value = "select * from movie where title=?", nativeQuery = true)
    Movie getAllMoviesNative(String title);

    @Query("select m from Movie m")
    List<Movie> getAllMovie();

    @Query("select m.title as title, m.genre as genre from Movie m")
    List<TitleAndGenre> getAllTitleAndGenre();

    @Query("select distinct(m.genre) from Movie m")
    List<String> getAllGenre();
    @Query("select m from Movie m where m.title like ?1")
    List<Movie> getAllMovieLike(String title);

    @Query("select count(m) from Movie m where m.genre = :genre")
    int findTotalMovieByGenres(@Param("genre") String genre);

    @Query("select m from Movie m where m.year >= ?1")
    List<Movie> getAllMovieWithYearGTE(Long year);

    @Query("select m from Movie m where m.year >= ?1 and m.genre like ?2")
    List<Movie> getAllMovieWithYearAndGenre(Long year, String genre);

    @Query("select m from Movie m where m.year between ?1 and ?2")
    List<Movie> getAllMoviesBetween(Long start, Long end);

    @Query("select m from Movie m join m.actors actors where actors.firstName like ?1 and actors.lastName like ?2")
    List<Movie> getAllMoviesWithActorIn(String firstName, String lastName);

    @Query("select m from Movie m join m.comments comments")
    List<Movie> getMoviesWithComments();

    @Query("select m from Movie m left join m.comments comments")
    List<Movie> getMoviesWithCommentsLeftJoin();

}
