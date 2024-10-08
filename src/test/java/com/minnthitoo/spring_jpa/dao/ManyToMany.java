package com.minnthitoo.spring_jpa.dao;

import com.minnthitoo.spring_jpa.model.entity.Director;
import com.minnthitoo.spring_jpa.model.entity.Gender;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import com.minnthitoo.spring_jpa.model.entity.Name;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Rollback(false)
public class ManyToMany {

    @Autowired
    private MovieDao movieDao;

    @Test
    @Transactional
    public void testManyToMany() {
        Optional<Movie> result = this.movieDao.findById(1L);
        Movie movie = result.get();

        Director director1 = new Director();

        Name name1 = new Name();
        name1.setFirstName("John");
        name1.setLastName("Smith");

        director1.setName(name1);
        director1.setBirthday(new Date());
        director1.setGender(Gender.MALE);

        movie.getDirectors().add(director1);

        Director director2 = new Director();
        Name name2 = new Name();
        name2.setFirstName("Jane");
        name2.setLastName("Doe");

        director2.setName(name2);

        director2.setBirthday(new Date());
        director2.setGender(Gender.FEMALE);

        movie.getDirectors().add(director2);

        director1.getMovies().add(movie);
        director2.getMovies().add(movie);

        this.movieDao.save(movie);

    }

}
