package com.yuritelkov.spring.test.book.book_test.controller;



import com.yuritelkov.spring.test.book.book_test.entity.Book;
import com.yuritelkov.spring.test.book.book_test.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {

    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> showAllBooks() {
        List<Book> allBooks = bookService.getAllBooks();

        return allBooks;
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable int id) {
        Book book = bookService.getBook(id);
        return book;
    }

    @PostMapping("books")
    public Book addNewBook(@RequestBody Book book){
        bookService.saveBook(book);
        return book;
    }

    @PutMapping("books")
    public Book updateBook(@RequestBody Book book){
        bookService.saveBook(book);
        return book;
    }

    @DeleteMapping("books/{id}")
    public String deleteEmployee(@PathVariable int id){
        bookService.deleteBook(id);
        return "Book with ID =  " + id + " was deleted";
    }

//    @GetMapping ("employees/name/{name}")
//    public  List<Book> showAllBooksByName(@PathVariable String name){
//        List<Book> books = bookService.findAllByName(name);
//        return books;
//    }

}
