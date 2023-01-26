package com.yuritelkov.spring.test.book.book_test.service;





import com.yuritelkov.spring.test.book.book_test.entity.Book;


import java.util.List;

public interface BookService {
    public List<Book> getAllBooks();

    public void saveBook (Book book);

    public Book getBook(int id);

    public void deleteBook(int id);

//    public List<Book> findAllByName(String name);
}
