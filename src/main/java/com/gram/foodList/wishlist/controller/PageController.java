package com.gram.foodList.wishlist.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/pages")
@Controller
public class PageController {

    @GetMapping("/main")
    public ModelAndView main(){
        return new ModelAndView("html/main");
    }

}
