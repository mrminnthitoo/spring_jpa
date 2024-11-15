package com.minnthitoo.spring_jpa.controller.api;

import com.minnthitoo.spring_jpa.model.entity.Book;
import com.minnthitoo.spring_jpa.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookApiController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks(){
        List<Book> books = this.bookRepository.findAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable Long bookId){
        Optional<Book> result = this.bookRepository.findById(bookId);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        Book savedBook = this.bookRepository.save(book);
        return ResponseEntity.ok(savedBook);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Book> updateBook(@PathVariable Long bookId, @RequestBody Book book){
        Optional<Book> result = this.bookRepository.findById(bookId);
        if (result.isPresent()){
            book.setId(bookId);
            Book updatedBook = this.bookRepository.save(book);
            return ResponseEntity.ok(updatedBook);
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Book> deleteBookById(@PathVariable Long bookId){
        Optional<Book> result = this.bookRepository.findById(bookId);
        if (result.isPresent()){
            this.bookRepository.delete(result.get());
            return ResponseEntity.ok(result.get());
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
