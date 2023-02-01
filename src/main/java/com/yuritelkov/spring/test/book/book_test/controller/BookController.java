package com.yuritelkov.spring.test.book.book_test.controller;

import com.yuritelkov.spring.test.book.book_test.entity.Book;
import com.yuritelkov.spring.test.book.book_test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(path = "/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(path = "")
    public String viewBooksList (
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ask") String order,
            Model uiModel
    ){
        Sort sort;
        if (order.equals("desc")) sort = Sort.by(Sort.Direction.DESC, sortBy);
        else sort = Sort.by(Sort.Direction.ASC, sortBy);

        //Нумерация страниц для Spring Data JPA начинается с 0
        Integer pageNumber = (page > 0) ? page - 1 : 0;
        PageRequest pageRequest = PageRequest.of(pageNumber, 10, sort);
        Page<Book> books = bookService.getAllByPage(pageRequest);

        uiModel.addAttribute("books", books.getContent());

        return "books/list";
    }
}
