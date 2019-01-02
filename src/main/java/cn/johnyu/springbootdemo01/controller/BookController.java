package cn.johnyu.springbootdemo01.controller;

import cn.johnyu.springbootdemo01.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class BookController {
    @Autowired
    private JdbcTemplate template;
    @Value("insert into books (bname,price) values(?,?)")
    private String addBookSql;
    @RequestMapping(value = "/books",method = RequestMethod.POST,consumes = "application/json")
    public String addBook(@RequestBody Book book){
        template.update(addBookSql,new Object[]{book.getBname(),book.getPrice()});
        return "suc";
    }
}
