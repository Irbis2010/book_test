package com.yuritelkov.spring.test.book.book_test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeAction {
    @RequestMapping("/")
    public String homeAction(
            @RequestParam(value="name", required=false, defaultValue="World") String name,
            Model uiModel
    ) {
        uiModel.addAttribute("name", name);
        return "index";
    }
}
