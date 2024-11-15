package com.minnthitoo.spring_jpa.dao;

import com.minnthitoo.spring_jpa.model.dto.GenreCountDto;
import com.minnthitoo.spring_jpa.model.dto.TitleAndGenre;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieDao extends CrudRepository<Movie, Long>, JpaSpecificationExecutor<Movie> {


    @Query(value = "select m from Movie m where m.title like %?1%")
    List<Movie> findByTitleNative(String title);

    @Query(value = "select movie from Movie movie")
    List<Movie> hqlGetAllMovies();

    @Query(value = "select movie.title as title, movie.genre as genre from Movie movie")
    List<TitleAndGenre> hqlGetAllMoviesWithProjection();

    @Query(value = "select genre, count(*) as count from movie group by genre;", nativeQuery = true)
    List<GenreCountDto> getGenreCount();

    @Query("select m from Movie m where m.title like ?1")
    List<Movie> getMovieByTitle(String title);

    @Query("select count(m) from Movie m where m.genre = :genre")
    int getGenreCountByGenre(@Param("genre") String genre);

    @Query("select m from Movie m where m.year >= ?1")
    List<Movie> getMovieByYear(Long year);

    @Query("select m from Movie m where m.year >= ?1 and m.genre like ?2")
    List<Movie> getMoviesByYearGTEAndGenre(Long year, String genre);

    @Query("select m from Movie m where m.year between ?1 and ?2")
    List<Movie> getMoviesByYearBetween(Long year1, Long year2);

    @Query("select m from Movie m join m.actors actors where actors.firstName like %?1% and actors.lastName like %?2%")
    List<Movie> getMoviesByFirstAndLastName(String firstName, String lastName);

    @Query("select m from Movie m join m.comments comments")
    List<Movie> getMoviesByComments();

    @Query("select m from Movie m left join m.comments comments")
    List<Movie> getMovieByCommentsLeftJoin();

    @Modifying
    @Transactional
    @Query("insert Movie(title) values (?1), (?2)")
    int insertTwoMovies(String title1, String title2);

}
