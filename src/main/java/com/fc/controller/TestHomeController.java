package com.fc.controller;

import com.fc.model.BookInfo;
import com.fc.service.BookService;
import com.google.common.collect.Lists;
import com.google.gson.Gson;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestHomeController {

  private final BookService bookService;

  @Autowired
  public TestHomeController(BookService bookService) {
    this.bookService = bookService;
  }

  @ResponseBody
  @RequestMapping("myhome")
  public String bookList() {
    List<BookInfo> list = Lists.newArrayList();
    return new Gson().toJson(list);
  }

  @RequestMapping("home1")
  public String home1() {
    // 在freemarker中没找到对应的模板，因此，去jsp中找
    return "my";
  }

  @RequestMapping("home2")
  public String home2() {
    // 在freemarker中有对应的模板，因此匹配aahome2.ftl
    return "home2";
  }

  @RequestMapping("home3")
  public String home3() {
    // 在jsp中有对应的模板，但freemarker中也有对应的模板，优先匹配ftl，因此匹配aahome3.ftl
    return "home3";
  }

  @RequestMapping("home4")
  public String home4() {
    // 在freemarker中有对应的模板，因此匹配aahome4.ftl
    return "home4";
  }

  @RequestMapping("home5")
  public String home5() {
    // 在jsp中有对应的模板，因此匹配 home5.jsp
    return "home5";
  }
}
