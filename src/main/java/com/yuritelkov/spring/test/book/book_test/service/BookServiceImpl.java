package com.yuritelkov.spring.test.book.book_test.service;


import com.yuritelkov.spring.test.book.book_test.dao.BookRepository;
import com.yuritelkov.spring.test.book.book_test.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void saveBook(Book book) {

        bookRepository.save(book);
    }

    @Override
    public Book getBook(int id) {
        Book book = null;
        Optional<Book> optional = bookRepository.findById(id);
        if (optional.isPresent()) {
            book = optional.get();
        }
        return book;
    }

    @Override
    public void deleteBook(int id) {
        bookRepository.deleteById(id);

    }


//
//    @Override
//    public List<Book> findAllByName(String name) {
//        List<Book> book =bookRepository.findAllByName(name);
//        return book;
//    }
}
