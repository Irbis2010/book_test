package com.yuritelkov.spring.test.book.book_test.controller;


import com.yuritelkov.spring.test.book.book_test.entity.Book;
import com.yuritelkov.spring.test.book.book_test.service.BookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/books/api")

public class MyRESTController {
    final Logger logger = LoggerFactory.getLogger(MyRESTController.class);

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public @ResponseBody
    List<Book> showAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable int id) {

        return bookService.getBook(id);
    }

    @GetMapping("")
    public @ResponseBody
    Page<Book> getPageBooks(

            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ask") String order
    ) {
        Sort sort;
        if (order.equals("desc")) sort = Sort.by(Sort.Direction.DESC, sortBy);
        else sort = Sort.by(Sort.Direction.ASC, sortBy);

        Integer pageNumber = (page > 0) ? page - 1 : 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, 10, sort);
        return bookService.getAllByPage(pageRequest);
    }

    @GetMapping(path = "/search")
    public @ResponseBody
    Page<Book> search(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ask") String order,
            @RequestParam(required = false, defaultValue = "") String term,
            @RequestParam(required = false, defaultValue = "") String afterYear,
            @RequestParam(required = false, defaultValue = "") String ready
    ) {
        Sort sort;
        if (order.equals("desc")) sort = Sort.by(Sort.Direction.DESC, sortBy);
        else sort = Sort.by(Sort.Direction.ASC, sortBy);

        Integer pageNumber = (page > 0) ? page - 1 : 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, 10, sort);

        if (!ready.equals("") && (ready.equals("true") || ready.equals("false"))) {
            return bookService.search(term, afterYear, Boolean.parseBoolean(ready), pageRequest);
        }
        return bookService.search(term, afterYear, pageRequest);
    }


    @PostMapping("")
    public Book addNewBook(@RequestBody Book book) {
        logger.info("Creating book: " + book);
        bookService.saveBook(book);
        logger.info("Book created successfully with info: " + book);
        return book;
    }

    @PutMapping("")
    public Book updateBook(@RequestBody Book book) {
        logger.info("Updating book: " + book);
        bookService.saveBook(book);
        logger.info("Book update successfully with info: " + book);
        return book;
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {

        logger.info("Deleting book with id: " + id);
        bookService.deleteBook(id);
        logger.info("book deleted successfully");
    }

}
