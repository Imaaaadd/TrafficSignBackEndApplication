package com.example.trafficsignproject.rest_controllers;

import com.example.trafficsignproject.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class RegisterApiController {

    @Autowired
    UserService userService;

    @PostMapping("/user/register")
    public ResponseEntity registerNewUser(@RequestParam("nom") String nom,
                                          @RequestParam("email") String email,
                                          @RequestParam("password") String password,
                                          @RequestParam("tel") String tel) {

        if (nom.isEmpty() || email.isEmpty() || password.isEmpty() || tel == null ) {
            return new ResponseEntity<>("S'il vous plait completer tous les champs", HttpStatus.BAD_REQUEST);
        }

        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        int result = userService.registerNewUserServiceMethod(nom, email, hashed_password, tel);

        if (result != 1) {
            return new ResponseEntity<>("Echec", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Succés", HttpStatus.OK);

    }

}
