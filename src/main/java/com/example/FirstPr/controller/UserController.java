package com.example.FirstPr.controller;

import com.example.FirstPr.domain.Role;
import com.example.FirstPr.domain.User;
import com.example.FirstPr.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping
    public String userList(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        boolean hadUserRole = authentication.getAuthorities().stream()
                .anyMatch(r -> r.getAuthority().equals("ADMIN"));

        if (!hadUserRole)
            return "redirect:/login";

        model.addAttribute("users", userRepo.findAll());
        return "userList";
    }


    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String delete(HttpServletRequest request, Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String curr_username = auth.getName();

        if (request.getParameter("delete") != null) {

            if (request.getParameter("idChecked") != null)

                for (String username : request.getParameterValues("idChecked"))
                    userRepo.delete(userRepo.findByUsername(username));

            if (userRepo.findByUsername(curr_username) == null)
                return "redirect:/login";

        } else if (request.getParameter("block") != null) {

            if (request.getParameter("idChecked") != null)

                for (String username: request.getParameterValues("idChecked")) {
                    User user = userRepo.findByUsername(username);
                    user.getRoles().clear();
                    user.getRoles().add(Role.valueOf("USER"));
                    userRepo.save(user);
                }

            User new_user = new User();
                new_user.setRoles(Collections.singleton(Role.USER));
            if (userRepo.findByUsername(curr_username).getRoles().equals(new_user.getRoles()))
                return "redirect:/login";
        } else {

            if (request.getParameter("idChecked") != null)

                for (String username: request.getParameterValues("idChecked")) {
                    User user = userRepo.findByUsername(username);
                    user.getRoles().clear();
                    user.getRoles().add(Role.valueOf("ADMIN"));
                    userRepo.save(user);
                }

        }

        return "redirect:/user";
    }


}
