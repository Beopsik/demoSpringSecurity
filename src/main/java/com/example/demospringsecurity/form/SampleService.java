package com.example.demospringsecurity.form;

import com.example.demospringsecurity.account.Account;
import com.example.demospringsecurity.account.AccountContext;
import com.example.demospringsecurity.common.SecurityLogger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class SampleService {

    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    public void dashboard() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails =(UserDetails) authentication.getPrincipal();
        System.out.println("===============");
        System.out.println(userDetails.getUsername());
    }

    @Async
    public void asyncService() {
        SecurityLogger.log("Async service");
        System.out.println("Async service is called.");
    }
}
