package com.minnthitoo.spring_jpa.dao.specification;

import com.minnthitoo.spring_jpa.model.entity.Actor;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serial;
import java.util.ArrayList;

public class MovieSpecification {
    public static Specification<Movie> getMovieByYear(Long year){
        return new Specification<Movie>() {
            @Serial
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.equal(root.<Long>get("year"), year);
            }
        };
    }

    public static Specification<Movie> getMovieByTitleAndYear(String title, Long year){
        return new Specification<Movie>() {
            @Override
            public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                ArrayList<Predicate> predicates = new ArrayList<>();
                if (title != null){
                    predicates.add(criteriaBuilder.equal(root.get("title"), title));
                }
                if (year != null){
                    predicates.add(criteriaBuilder.equal(root.get("year"), year));
                }
                Predicate and = criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
                query.where(and);
                return and;
            }
        };
    }

    public static Specification<Movie> getMovieByYearGreaterThan(Long year){
        return new Specification<Movie>() {
            @Serial
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Movie> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.ge(root.<Long>get("year"), year);
            }
        };
    }

    public static Specification<Movie> getMovieWhereActorIn(String actor){
        return ((root, query, criteriaBuilder) -> {
            Join<Movie, Actor> actors = root.join("actors");
            return criteriaBuilder.equal(actors.get("name").get("fullName"), actor);
        });
    }
}
