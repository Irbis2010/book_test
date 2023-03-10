package com.yuritelkov.spring.test.book.book_test.controller;


import com.yuritelkov.spring.test.book.book_test.dao.BookRepository;
import com.yuritelkov.spring.test.book.book_test.entity.Book;
import com.yuritelkov.spring.test.book.book_test.service.BookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("")

public class MyRESTController {
    final Logger logger = LoggerFactory.getLogger(MyRESTController.class);

    @Autowired
    private BookService bookService;


    @GetMapping("/book")
    public String viewHomePage(Model model) {
        return findPaginated(1, "title", "asc", model);
    }

    @GetMapping("/showNewBookForm")
    public String showNewBookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "books/new_book";
    }

    @PostMapping("/saveBook")
    public String saveBook(@ModelAttribute("book") Book book) {
        //сохраняем книгу в базу данных
        bookService.saveBook(book);
        return "redirect:/book";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {

        //get book from the service
        Book book = bookService.getBookById(id);

        //set book as a model attribute to pre-populate the form
        model.addAttribute("book", book);
        return "books/update_book";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable(value = "id") int id) {

        bookService.deleteBook(id);

        return "redirect:/book";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        int pageSize = 5;

        Page<Book> page = bookService.findPaginated(pageNo, pageSize, sortField, sortDir);
        List<Book> bookList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("bookList", bookList);
        return "books/book";

    }

    @GetMapping("/search")
    public String search(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "books/search";
    }

    @GetMapping("/searchTitle")
    public String findAll(
            @RequestParam Optional<String> title, Model model) {

        Page<Book> page = bookService.findByTitle(title.orElse("_"), Pageable.unpaged());
        List<Book> bookList = page.getContent();
        model.addAttribute("bookList", bookList);

        return "books/book";

    }
}
