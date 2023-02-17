package com.yuritelkov.spring.test.book.book_test.controller;


import com.yuritelkov.spring.test.book.book_test.entity.Book;
import com.yuritelkov.spring.test.book.book_test.service.BookService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("")

public class MyRESTController {
    final Logger logger = LoggerFactory.getLogger(MyRESTController.class);

    @Autowired
    private BookService bookService;

    @GetMapping("/book")
    public String viewHomePage(Model model) {
        return findPaginated(1, model);
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
//                                @RequestParam("sortField") String sortField,
//                                @RequestParam("sortDir") String sortDir,
                                Model model) {

        int pageSize = 5;

        Page<Book> page = bookService.findPaginated(pageNo, pageSize);
        List<Book> bookList = page.getContent();

        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

//        model.addAttribute("sortField", sortField);
//        model.addAttribute("sortDir", sortDir);
//        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("bookList", bookList);
        return "books/book";

    }



//
//    @GetMapping("")
//    public @ResponseBody
//    Page<Book> getPageBooks(
//
//            @RequestParam(required = false, defaultValue = "1") Integer page,
//            @RequestParam(required = false, defaultValue = "id") String sortBy,
//            @RequestParam(required = false, defaultValue = "ask") String order
//    ) {
//        Sort sort;
//        if (order.equals("desc")) sort = Sort.by(Sort.Direction.DESC, sortBy);
//        else sort = Sort.by(Sort.Direction.ASC, sortBy);
//
//        Integer pageNumber = (page > 0) ? page - 1 : 0;
//        PageRequest pageRequest = PageRequest.of(pageNumber, 10, sort);
//        return bookService.getAllByPage(pageRequest);
//    }
//
//    @GetMapping(path = "/search")
//    public @ResponseBody
//    Page<Book> search(
//            @RequestParam(required = false, defaultValue = "1") Integer page,
//            @RequestParam(required = false, defaultValue = "id") String sortBy,
//            @RequestParam(required = false, defaultValue = "ask") String order,
//            @RequestParam(required = false, defaultValue = "") String term,
//            @RequestParam(required = false, defaultValue = "") String afterYear,
//            @RequestParam(required = false, defaultValue = "") String ready
//    ) {
//        Sort sort;
//        if (order.equals("desc")) sort = Sort.by(Sort.Direction.DESC, sortBy);
//        else sort = Sort.by(Sort.Direction.ASC, sortBy);
//
//        Integer pageNumber = (page > 0) ? page - 1 : 0;
//        PageRequest pageRequest = PageRequest.of(pageNumber, 10, sort);
//
//        if (!ready.equals("") && (ready.equals("true") || ready.equals("false"))) {
//            return bookService.search(term, afterYear, Boolean.parseBoolean(ready), pageRequest);
//        }
//        return bookService.search(term, afterYear, pageRequest);
//    }
//
//
//    @PostMapping("")
//    @ResponseStatus(HttpStatus.CREATED)
//    public @ResponseBody
//    Book addNewBook(@RequestBody Book book) {
//        logger.info("Creating book: " + book);
//        bookService.saveBook(book);
//        logger.info("Book created successfully with info: " + book);
//        return book;
//    }
//
//    @PutMapping("")
//    public @ResponseBody
//    Book updateBook(@RequestBody Book book) {
//        logger.info("Updating book: " + book);
//        bookService.saveBook(book);
//        logger.info("Book update successfully with info: " + book);
//        return book;
//    }
//
//    @DeleteMapping("/{id}")
//    public @ResponseBody
//    ResponseEntity<?> deleteBook(@PathVariable int id) {
//
//        logger.info("Deleting book with id: " + id);
//        bookService.deleteBook(id);
//        logger.info("book deleted successfully");
//        return ResponseEntity.ok("deleted Book #" + id);
//    }

}
