package com.example.trafficsignproject.rest_controllers;

import com.example.trafficsignproject.models.Login;
import com.example.trafficsignproject.models.User;
import com.example.trafficsignproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LoginApiController {

    @Autowired
    UserService userService;

    @PostMapping("/user/login")
    public ResponseEntity authenticateUser(@RequestBody Login login) {

        List<String> userEmail = userService.checkUserEmail(login.getEmail());

        if (userEmail.isEmpty() || userEmail == null) {
            return new ResponseEntity("l'email n'existe pas", HttpStatus.NOT_FOUND);
        }

        String hashedPassword = userService.checkUserPasswordByEmail(login.getEmail());

        if (!BCrypt.checkpw(login.getPassword(), hashedPassword)) {
            return new ResponseEntity("Email ou mot de passe est incorrecte", HttpStatus.BAD_REQUEST);
        }

        User user = userService.getUserDetailsByEmail(login.getEmail());
        return new ResponseEntity(user, HttpStatus.OK);
    }

}
