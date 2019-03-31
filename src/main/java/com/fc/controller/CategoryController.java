package com.fc.controller;

import com.fc.model.Category;
import com.fc.service.CategoryService;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/manage/category")
public class CategoryController {

  private final CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("add")
  public String addCategoryPage() {
    return "manage/addCategory";
  }

  @GetMapping("list")
  public ModelAndView listPage(ModelAndView modelAndView) {
    modelAndView.addObject("cList", categoryService.list());
    modelAndView.setViewName("manage/listCategory");
    return modelAndView;
  }

  @PostMapping(value = "add")
  @ResponseBody
  public ModelMap add(@RequestBody Category category) {
    categoryService.save(category);
    ModelMap modelMap = new ModelMap();
    modelMap.addAttribute("redirect", "add");
    return modelMap;
  }

  @PostMapping(value = "del")
  @ResponseBody
  public ModelMap del(@RequestBody JSONObject jsonObj) {
    Number id = jsonObj.getAsNumber("id");
    ModelMap modelMap = new ModelMap();
    categoryService.deleteById(id.intValue());
    return modelMap;
  }
}
