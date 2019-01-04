package cn.johnyu.springbootdemo01.controller;

import cn.johnyu.springbootdemo01.pojo.Book;
import cn.johnyu.springbootdemo01.repository.BookRepository;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    private BookRepository bookRepository;

    @Transactional
    @RequestMapping(method = {RequestMethod.POST},consumes = "application/json")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
       return new ResponseEntity<Book>(bookRepository.save(book), HttpStatus.CREATED);

    }
    @Transactional
    @RequestMapping(value = {"{id}"},method = {RequestMethod.PUT},consumes = "application/json")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable Integer id){
        System.out.println("update book id is: "+id);
        return new ResponseEntity<Book>(bookRepository.save(book),HttpStatus.RESET_CONTENT);
    }
    @GetMapping
    public ResponseEntity<List<Book>> findAllBooks(){
        List<Book> books=new ArrayList<>();
        bookRepository.findAll().forEach(book -> books.add(book));
        return  new ResponseEntity<>(books,HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Book> findBookById(@PathVariable Integer id){
        return new ResponseEntity<>(bookRepository.findById(id).orElse(new Book()),HttpStatus.OK);
    }



    @DeleteMapping(value = "{id}")
    public ResponseEntity<Book> deleteBook(@PathVariable Integer id){
        Book book=new Book();
        try{
            bookRepository.deleteById(id);
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(200).body(new Book());


    }

    public static void main(String[] args) {
        ResponseEntity<Book> r = new ResponseEntity<>(new Book(), HttpStatus.OK);

    }
}
