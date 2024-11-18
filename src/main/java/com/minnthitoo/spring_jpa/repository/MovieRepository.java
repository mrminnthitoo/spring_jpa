package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.dto.TitleAndGenre;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "select * from movie where title=?", nativeQuery = true)
    Movie getAllMoviesNative(String title);

    @Query("select m from Movie m")
    List<Movie> getAllMovie();

    @Query("select m.title as title, m.genre as genre from Movie m")
    List<TitleAndGenre> getAllTitleAndGenre();

}
