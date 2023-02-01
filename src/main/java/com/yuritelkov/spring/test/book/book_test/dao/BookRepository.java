package com.yuritelkov.spring.test.book.book_test.dao;

import com.yuritelkov.spring.test.book.book_test.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface BookRepository extends JpaRepository<Book, Integer> {

    @Query("SELECT t FROM Book t WHERE " +
           "(LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(t.author) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(t.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) " +
           "AND t.printYear > :printYear")
    Page<Book> getBySearchParams(
            @Param("searchTerm") String searchTerm,
            @Param("printYear") String printYear,
            Pageable pageRequest
    );

    @Query("SELECT t FROM Book t WHERE " +
           "(LOWER(t.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(t.author) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(t.description) LIKE LOWER(CONCAT('%', :searchTerm, '%'))) " +
           "AND t.printYear > :printYear AND t.readAlready = :readAlReady")
    Page<Book> getBySearchParamsAndReadAlready(
            @Param("searchTerm") String searchTerm,
            @Param("printYear") String printYear,
            @Param("readAlReady") boolean readAlReady,
            Pageable pageRequest
    );

}
