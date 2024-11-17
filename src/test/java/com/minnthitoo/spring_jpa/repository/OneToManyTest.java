package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.entity.Comment;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback(value = false)
public class OneToManyTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    @Transactional
    public void testOneToMany(){
        Movie movie = this.movieRepository.findById(2L).get();

        Comment comment = new Comment();
        comment.setComment("Comment 1");

        Comment comment2 = new Comment();
        comment2.setComment("Comment 2");

        movie.getComments().add(comment);
        movie.getComments().add(comment2);

        comment.setMovie(movie);
        comment2.setMovie(movie);

        this.movieRepository.save(movie);
    }

    @Test
    @Transactional
    public void orphanRemovalTest(){
        this.movieRepository.deleteById(2L);
    }


}
