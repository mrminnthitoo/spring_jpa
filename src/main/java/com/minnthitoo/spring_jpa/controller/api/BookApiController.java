package com.minnthitoo.spring_jpa.controller.api;

import com.minnthitoo.spring_jpa.dao.BookDao;
import com.minnthitoo.spring_jpa.model.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/books")
public class BookApiController {
    @Autowired
    BookDao bookDao;

    @GetMapping
    public List<Book> getAllBooks() {
        return this.bookDao.findAll();
    }

    @GetMapping("/{bookId")
    public Book getBookById(@PathVariable("bookId") Long bookId) {
        return this.bookDao.findById(bookId).get();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return this.bookDao.save(book);
    }

    @PutMapping("/{bookId}")
    public Book updateBook(@PathVariable("bookId") Long bookId, @RequestBody Book book) {
        if (this.bookDao.existsById(bookId)) {
            return this.bookDao.save(book);
        }else {
            return null;
        }
    }

    @DeleteMapping
    public void deleteBook(@PathVariable("bookId") Long bookId) {
        this.bookDao.deleteById(bookId);
    }

}
