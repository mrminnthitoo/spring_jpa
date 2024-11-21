package com.minnthitoo.spring_jpa.repository.specification;

import com.minnthitoo.spring_jpa.model.entity.Actor;
import com.minnthitoo.spring_jpa.model.entity.Movie;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class MovieSpecification {

    /*
    * .greaterThan, .ge, .equal, greaterThanOrEqual ... are operators
     */

    public static Specification<Movie> getMovieByYear(Long i){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.<Long>get("year"), i);
    }

    public static Specification<Movie> getMovieByTitleOrYear(String title, Long year){
        return ((root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();
            if (title != null){
                predicates.add(criteriaBuilder.equal(root.get("title"), title));
            }
            if (year != null){
                predicates.add(criteriaBuilder.equal(root.get("year"), year));
            }
            Predicate and = criteriaBuilder.and(predicates.toArray(new Predicate[] {}));
            assert query != null;
            query.where(and);
            return and;
        });
    }

    public static Specification<Movie> getMovieByYearGreaterThan(Long i){
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.<Long>get("year"), i);
    }

    public static Specification<Movie> getMovieByYearBetween(Long start, Long end){
        return (root, query, criteriaBuilder) -> criteriaBuilder.between(root.<Long>get("year"), start, end);
    }

    public static Specification<Movie> getMovieByTitleLike(String title){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    public static Specification<Movie> getMovieWhereActorIn(String actor){
        return ((root, query, criteriaBuilder) -> {
            Join<Movie, Actor> actors = root.join("actors");
            return criteriaBuilder.equal(actors.get("fullName"), actor);
        });
    }

    public static Specification<Movie> getMovieGroupByGenre(String genres){
        return ((root, query, criteriaBuilder) -> {
            assert query != null;
            query.orderBy(criteriaBuilder.asc(root.get("genre")));
            return query.getRestriction();
        });
    }

    public static Specification<Movie> getMovieInGenre(List<String> genres){
        return ((root, query, criteriaBuilder) -> {
            CriteriaBuilder.In<String> inClause = criteriaBuilder.in(root.get("genre"));
            for (String genre : genres){
                inClause.value(genre);
            }
            return inClause;
        });
    }
}
