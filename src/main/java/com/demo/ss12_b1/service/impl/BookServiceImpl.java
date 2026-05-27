package com.demo.ss12_b1.service.impl;

import com.demo.ss12_b1.model.entity.Book;
import com.demo.ss12_b1.service.BookService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final List<Book> books = new ArrayList<>();
    private Long currentId = 1L;

    @Override
    public List<Book> findAll() {
        return books;
    }

    @Override
    public Book findById(Long id) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }

        throw new RuntimeException("Khong tim thay sach co id: " + id);
    }

    @Override
    public Book create(Book book) {
        if (book.getTitle() == null || book.getAuthor() == null || book.getPrice() == null) {
            throw new RuntimeException("Du lieu sach khong hop le");
        }

        book.setId(currentId);
        currentId++;
        books.add(book);

        return book;
    }

    @Override
    public Book update(Long id, Book book) {
        Book oldBook = findById(id);
        oldBook.setTitle(book.getTitle());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setPrice(book.getPrice());

        return oldBook;
    }

    @Override
    public void delete(Long id) {
        Book book = findById(id);
        books.remove(book);
    }
}
