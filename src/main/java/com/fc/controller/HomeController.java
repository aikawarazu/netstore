package com.fc.controller;

import com.fc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

  private final BookController bookController;
  private final BookService bookService;

  @Autowired
  public HomeController(BookService bookService, BookController bookController) {
    this.bookService = bookService;
    this.bookController = bookController;
  }

  @RequestMapping({"", "/", "index"})
  public ModelAndView index(
      @RequestParam(required = false) Long categoryId,
      @RequestParam(required = false, defaultValue = "1") Integer page,
      @RequestParam(required = false, defaultValue = "3") Integer pageSize) {
    ModelAndView modelAndView = bookController.bookList(categoryId, page, pageSize);
    modelAndView.setViewName("index");
    return modelAndView;
  }
}
