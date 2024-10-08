package com.minnthitoo.spring_jpa.dao;

import com.minnthitoo.spring_jpa.model.entity.Comment;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
@Rollback(false)
public class OneToMany {

    @Autowired
    private MovieDao movieDao;

    @Test
    @Transactional
    public void testOneToMany() {
        Optional<Movie> result = this.movieDao.findById(1L);
        Movie movie = result.get();

        Comment comment = new Comment();
        comment.setComment("Comment 1");

        Comment comment2 = new Comment();
        comment2.setComment("Comment 2");

        movie.getComments().add(comment);
        movie.getComments().add(comment2);

        this.movieDao.save(movie);

    }

}
