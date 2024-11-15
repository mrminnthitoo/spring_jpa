package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.entity.Book;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookRepositoryQueryMethodTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void test(){
        List<Book> books = this.bookRepository.findAll();
        assertFalse(books.isEmpty());
    }

    @Test
    public void findByYearTest(){
        List<Book> books = this.bookRepository.findByYear(2010L);
        for (Book book : books){
            log.info("{}", book);
        }
    }

    @Test
    public void findByYearAndTitleTest(){
        List<Book> books = this.bookRepository.findByYearAndTitle(2010L, "Book 1");
        for (Book book : books){
            log.info("{}", book);
        }
    }

    @Test
    public void findByYearOrTitle(){
        List<Book> books = this.bookRepository.findByYearOrTitle(2010L, "Book 1");
        for (Book book : books){
            log.info("{}", book);
        }
    }

    @Test
    public void findByYearGreaterThan(){
        List<Book> books = this.bookRepository.findByYearGreaterThan(2010L);
        for (Book book : books){
            log.info("{}", book);
        }
    }

    @Test
    public void findDistinctByYearTest(){
        List<Book> books = this.bookRepository.findDistinctByYear(2010L);
        for (Book book : books){
            log.info("{}", book);
        }
    }

    @Test
    public void findByTitleLikeTest(){
        List<Book> books = this.bookRepository.findByTitleLike("%Book%");
        for (Book book : books){
            log.info("{}", book);
        }
    }

    @Test
    public void findByTitleLikeOrderByYearDescTest(){
        List<Book> books = this.bookRepository.findByTitleLikeOrderByYearDesc("Book%");
        for (Book book : books){
            log.info("{}", book);
        }
    }

    @Test
    public void findByYearInTest(){
        List<Long> years = new ArrayList<>();
        years.add(2010L);
        years.add(2020L);
        List<Book> books = this.bookRepository.findByYearIn(years);
        for (Book book : books){
            log.info("{}", book);
        }
    }

}
