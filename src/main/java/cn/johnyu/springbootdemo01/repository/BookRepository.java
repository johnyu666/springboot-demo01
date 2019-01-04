package cn.johnyu.springbootdemo01.repository;

import cn.johnyu.springbootdemo01.pojo.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {

}
