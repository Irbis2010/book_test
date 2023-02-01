package com.yuritelkov.spring.test.book.book_test.service;


import com.yuritelkov.spring.test.book.book_test.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    void saveBook (Book book);

    Book getBook(int id);

    void deleteBook(int id);

    Page<Book> getAllByPage(Pageable pageable);


    Page<Book> search(String term, String printYear, Pageable pageable);
    Page<Book> search(String term, String printYear, boolean readAlReady, Pageable pageable);

}
