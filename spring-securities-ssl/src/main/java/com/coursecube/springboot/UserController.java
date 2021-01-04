package com.coursecube.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@SessionAttributes("MyRoles")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public String showIndexPage(HttpSession session){
        System.out.println("IndexController - showIndexPage()");
        setUsersRoleInSession(session);
        return "index";
    }
    @GetMapping("/deleteBook")
    public String showDeleteBookPage(HttpSession session) {
        System.out.println("** showDeleteBookPage **");
        setUsersRoleInSession(session);
        return "deleteBook";
    }
    @GetMapping("/viewBooks")
    public String showViewBooksPage(HttpSession session) {
        System.out.println("** showViewBooksPage **");
        setUsersRoleInSession(session);
        return "viewBooks";
    }
    @GetMapping("/addBook")
    public String showAddBookPage(HttpSession session) {
        System.out.println("** showAddBookPage **");
        setUsersRoleInSession(session);
        return "addBook";
    }
    @GetMapping("/editBook")
    public String showEditBookPage(HttpSession session) {
        System.out.println("** showEditBookPage **");
        setUsersRoleInSession(session);
        return "editBook";
    }
    @GetMapping("/placeOrder")
    public String showPlaceOrderPage(HttpSession session) {
        System.out.println("** showPlaceOrderPage **");
        setUsersRoleInSession(session);
        return "placeOrder";
    }
    //customizing login page
    @GetMapping("/login")
    public String loginPage(
            @RequestParam(value = "error",required = false) String error,
            @RequestParam(value = "logout",required = false) String logout,
            HttpSession session) {
        //Get the Cart Data if any and store in Session
        //Get the Last Accessed Time from DB.

        System.out.println("** loginPage **");
        setUsersRoleInSession(session);
        return "mylogin";
    }
    //customizing logout page
    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        //Store the Cart Data if any into the database
        //Tasks At Logout time
        System.out.println("** logoutPage **");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @GetMapping("/showRegister")
    public String showRegisterPage(Model model) {
        System.out.println("** showRegisterPage **");
        User myUser = new User();
        model.addAttribute("myuser",myUser);
        return "register";
    }
    @PostMapping("/registerMyUser")
    public String registerUser(@ModelAttribute("myuser")User user, BindingResult bindingResult, Model model) {
        System.out.println("** registerUser **");
        //Do Validations
        userService.registerUser(user);
        return "index";
    }

    private List<String> getUserRoles() {
        List<String> myroles = new ArrayList<>();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            Collection<GrantedAuthority> col = (Collection<GrantedAuthority>) auth.getAuthorities();
            for (GrantedAuthority gauth : col)
                myroles.add(gauth.getAuthority());
        }
        return myroles;
    }

    private void setUsersRoleInSession(HttpSession session){
        List<String> myroles = getUserRoles();
        System.out.println(myroles);
        session.setAttribute("MyRoles", myroles);
    }
}
