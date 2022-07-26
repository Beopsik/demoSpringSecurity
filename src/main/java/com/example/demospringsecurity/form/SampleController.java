package com.example.demospringsecurity.form;

import com.example.demospringsecurity.account.Account;
import com.example.demospringsecurity.account.AccountRepository;
import com.example.demospringsecurity.account.UserAccount;
import com.example.demospringsecurity.book.BookRepository;
import com.example.demospringsecurity.common.CurrentUser;
import com.example.demospringsecurity.common.SecurityLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.concurrent.Callable;

@Controller
public class SampleController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    SampleService sampleService;

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/")
    public String index(Model model, @CurrentUser Account account){
        if (account == null) {
            model.addAttribute("message", "Hello Spring Security");
        }else{
            model.addAttribute("message", "Hello " + account.getUsername());
        }
        return "index";
    }

    @GetMapping("/info")
    public String info(Model model){
        model.addAttribute("message", "Info");
        return "info";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, Principal principal){
        model.addAttribute("message", "Hello "+principal.getName());
        sampleService.dashboard();
        return "dashboard";
    }

    @GetMapping("/admin")
    public String admin(Model model, Principal principal){
        model.addAttribute("message", "Hello Admin, "+principal.getName());
        return "admin";
    }

    @GetMapping("/user")
    public String user(Model model, Principal principal){
        model.addAttribute("message", "Hello User, "+principal.getName());
        model.addAttribute("books", bookRepository.finCurrentUserBooks());
        return "user";
    }

    @GetMapping("/async-service")
    @ResponseBody
    public String asyncService() {
        SecurityLogger.log("MVC, before async service");
        sampleService.asyncService();
        SecurityLogger.log("MVC, after async service");
        return "Async Service";
    }
}
