package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    // query methods

    /*
    findBy -> select
    Year -> field
     */
    List<Book> findByYear(Long year);

    /*
    findBy -> select
    Year -> field
    And -> And Operator
    Title -> field
     */
    List<Book> findByYearAndTitle(Long year, String title);

    /*
    findBy -> select
    Year -> field
    Or -> Or Operator
    Title -> field
     */
    List<Book> findByYearOrTitle(Long year, String title);

    /*
    findBy -> select
    Year -> field
    GreaterThan -> Operator (Operators -> GreaterThanEqual, LessThan, LessThanEqual)
    e.g.
        findByYearGreaterThanEqual, findByYearLessThan, findByYearLessThanEqual
     */
    List<Book> findByYearGreaterThan(Long year);

    /*
    findDistinctBy -> select unique fields
    Year -> field
     */
    List<Book> findDistinctByYear(Long year);

    /*
    findBy -> select
    Title -> field
    Like -> operator (Operators -> NotLike)
    e.g.
    findByTitleLike(String title)
    if you want title_whatever, use title% in method call
    if you want whatever_title, use %title in method call
    if you want whatever_title_whatever, use %title% in method call

    related operators
    (StartingWith, EndingWith)
     */
    List<Book> findByTitleLike(String title);

    /*
    findBy -> select
    Title -> field
    Like -> operator
    OrderBy -> operator
    Year -> field
    Desc -> operator (descending), Asc(Ascending)
     */
    List<Book> findByTitleLikeOrderByYearDesc(String title);

    /*
    findBy -> select
    Year -> field
    In -> Operator
     */
    List<Book> findByYearIn(List<Long> years);


}
