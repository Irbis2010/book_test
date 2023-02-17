package com.yuritelkov.spring.test.book.book_test.service;


import com.yuritelkov.spring.test.book.book_test.entity.Book;
import org.springframework.data.domain.Page;



import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    void saveBook (Book book);

    Book getBookById(int id);

    void deleteBook(int id);

    Page<Book> findPaginated(int pageNo, int pageSize);



}

