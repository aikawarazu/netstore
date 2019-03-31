package com.fc.controller;

import com.fc.service.impl.BaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cart")
public class CartController extends BaseService {

//  private final CartService cartService;
//  @Autowired(required = false)
//  public CartController(CartService cartService) {
//    this.cartService = cartService;
//  }


  @PostMapping("add")
  public void add(Model model) {

//    cartService.save()
    model.addAttribute("", "");
  }

}
