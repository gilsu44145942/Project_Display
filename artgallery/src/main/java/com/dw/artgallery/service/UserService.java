package com.dw.artgallery.service;

import com.dw.artgallery.model.User;
import com.dw.artgallery.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public User getCurrentUser(HttpServletRequest request) {
        return null;
    }
}
