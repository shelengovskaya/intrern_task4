package com.example.FirstPr.controller;

import com.example.FirstPr.domain.Role;
import com.example.FirstPr.domain.User;
import com.example.FirstPr.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Date;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        User userFromDb = userRepo.findByUsername(user.getUsername());

        if (userFromDb != null) {
            model.put("message", "User exists!");
            return "registration";
        }
        user.setEmail(user.getEmail());
        Date date = new Date();
        user.setRegDate(date);
        user.setLastLogin(date);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        userRepo.save(user);

        return "redirect:/login";
    }
}
