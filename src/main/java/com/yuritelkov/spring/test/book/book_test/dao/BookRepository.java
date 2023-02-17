package com.yuritelkov.spring.test.book.book_test.dao;

import com.yuritelkov.spring.test.book.book_test.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;



public interface BookRepository extends JpaRepository<Book, Integer> {


}
