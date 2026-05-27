package com.demo.ss12_b1.service;

import com.demo.ss12_b1.model.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> findAll();

    Book findById(Long id);

    Book create(Book book);

    Book update(Long id, Book book);

    void delete(Long id);
}
