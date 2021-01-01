package com.coursecube.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class IndexController {

    @GetMapping("/")
    public String showIndexPage(){
        System.out.println("IndexController - showIndexPage()");
        return "index";
    }
    @GetMapping("/deleteBook")
    public String showDeleteBookPage(Model model) {
        model.getAttribute("abc");
        System.out.println("** showDeleteBookPage **");
        return "deleteBook";
    }
}
