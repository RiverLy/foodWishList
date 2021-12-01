package com.gram.foodList.wishlist.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RequestMapping("/pages")
@Controller
public class PageController {

    @GetMapping("/main")
    public ModelAndView main(){
        return new ModelAndView("html/main");
    }

    @GetMapping("/wishlist")
    public ModelAndView wishlist(){
        log.info("controller received");
        return new ModelAndView("html/wishlist");
    }

}
