package com.coursecube.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserController {

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
    @GetMapping("/viewBooks")
    public String showViewBooksPage(Model model) {
        Object obj = model.getAttribute("abc");
        System.out.println("** showViewBooksPage **"+obj);
        return "viewBooks";
    }
    @GetMapping("/addBook")
    public String showAddBookPage(Model model) {
        System.out.println("** showAddBookPage **");
        return "addBook";
    }
    @GetMapping("/editBook")
    public String showEditBookPage(Model model) {
        System.out.println("** showEditBookPage **");
        return "editBook";
    }
    @GetMapping("/placeOrder")
    public String showPlaceOrderPage(Model model) {
        System.out.println("** showPlaceOrderPage **");
        return "placeOrder";
    }
}
