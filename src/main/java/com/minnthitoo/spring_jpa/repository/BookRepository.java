package com.minnthitoo.spring_jpa.repository;

import com.minnthitoo.spring_jpa.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
