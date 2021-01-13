package com.fils.advancedlogin.controller;

import com.fils.advancedlogin.model.User;
import com.fils.advancedlogin.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserRepository urepo;

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/signup")
    public String getSignup() {
        return "signup";
    }

    @RequestMapping("/login")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/addUser")
    public ModelAndView addUser(@RequestParam("user_email") String user_email, User user) {
        ModelAndView mv = new ModelAndView("success");
        List<User> list = urepo.findByEmail(user_email);

        if (list.size() != 0) {
            mv.addObject("message", "Oops!  There is already a user registered with the email provided.");

        } else {
            urepo.save(user);
            mv.addObject("message", "User has been successfully registered.");
        }

        return mv;
    }

    @PostMapping("/login")
    public String login_user(@RequestParam("username") String username, @RequestParam("password") String password,
                             @RequestParam("answer") String answer, HttpSession session, ModelMap modelMap) {

        User auser = urepo.findByUsernamePassword(username, password);

        if (auser != null) {
            String uname = auser.getUser_email();
            String upass = auser.getUser_pass();
            String uans = auser.getUser_answer();

            if (username.equalsIgnoreCase(uname) && password.equalsIgnoreCase(upass) && answer.equalsIgnoreCase(uans)) {
                session.setAttribute("user", auser);
                return "userPage";
            } else {
                modelMap.put("error", "Invalid Account");
                return "login";
            }
        } else {
            modelMap.put("error", "Invalid Account");
            return "login";
        }

    }

    @GetMapping(value = "/logout")
    public String logout_user(HttpSession session) {
        session.removeAttribute("username");
        session.invalidate();
        return "redirect:/login";
    }


}
