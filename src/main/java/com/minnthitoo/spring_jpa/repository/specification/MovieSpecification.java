package com.minnthitoo.spring_jpa.repository.specification;

import com.minnthitoo.spring_jpa.model.entity.Movie;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;

public class MovieSpecification {
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
}
