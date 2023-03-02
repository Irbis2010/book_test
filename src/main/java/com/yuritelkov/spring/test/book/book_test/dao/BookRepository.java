package com.yuritelkov.spring.test.book.book_test.dao;

import com.yuritelkov.spring.test.book.book_test.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;




public interface BookRepository extends JpaRepository<Book, Integer> {
    @Query("select t from Book t where t.title like %?1%")
    Page<Book> findByTitle(String title, Pageable pageable);

}
