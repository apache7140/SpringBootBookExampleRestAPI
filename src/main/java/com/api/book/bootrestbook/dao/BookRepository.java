package com.api.book.bootrestbook.dao;

import com.api.book.bootrestbook.entities.Book;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Integer> {
    public Book findById(int id);

    public List<Book> findByAuthorStartingWith(String prefix);

    public List<Book> findByTitleStartingWith(String prefix); 

    public List<Book> findByAuthorContaining(String keyword);

    public List<Book> findByTitleContaining(String titleString);
}
