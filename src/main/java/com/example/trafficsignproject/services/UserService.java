package com.example.trafficsignproject.services;

import com.example.trafficsignproject.models.User;
import com.example.trafficsignproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public int registerNewUserServiceMethod(String nom, String email, String password, String tel) {
        return this.userRepository.registerNewUser(nom, email, password, tel);
    }

    public List<String> checkUserEmail(String email) {
        return userRepository.checkUserEmail(email);
    }

    public String checkUserPasswordByEmail(String email) {
        return userRepository.checkUserPasswordByEmail(email);
    }

    public User getUserDetailsByEmail(String email) {
        return userRepository.getUserDetailsByEmail(email);
    }

}
