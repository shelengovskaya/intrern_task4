package com.example.FirstPr.controller;

import com.example.FirstPr.domain.User;
import com.example.FirstPr.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@Controller
public class MainController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/")
    public String greeting(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepo.findByUsername(auth.getName());
        user.setLastLogin(new Date());
        userRepo.save(user);

        return "user";
    }

}