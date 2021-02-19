package com.example.FirstPr.service;

import com.example.FirstPr.domain.User;
import com.example.FirstPr.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public void updateLastLoginDate(String username) {
        User user = userRepo.findByUsername(username);
        user.setLastLogin(new Date());
        userRepo.save(user);
    }

}
