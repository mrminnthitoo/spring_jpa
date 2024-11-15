package com.minnthitoo.spring_jpa.dao;

import com.minnthitoo.spring_jpa.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends JpaRepository<Book, Long> {

    @Query(value = "select b from Book b where b.title=?1")
    Book findByTitle(String title);

    @Query(value = "select * from book where title=?1", nativeQuery = true)
    Book findByTitleNative(String title);

}
