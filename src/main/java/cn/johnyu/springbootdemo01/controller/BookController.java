package cn.johnyu.springbootdemo01.controller;

import cn.johnyu.springbootdemo01.pojo.Book;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@RestController
public class BookController {
    @PersistenceContext
    private EntityManager manager;
    @Transactional
    @RequestMapping(value = "/books",method = RequestMethod.POST,consumes = "application/json")
    public Book addBook(@RequestBody Book book){
        manager.persist(book);
        return book;
    }
}
