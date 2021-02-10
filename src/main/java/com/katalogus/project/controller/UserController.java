package com.katalogus.project.controller;

import com.katalogus.project.service.UserProvider;
import com.katalogus.project.utility.RegistrationData;
import com.katalogus.project.utility.UserCredentials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Service
@CrossOrigin
@RestController
public class UserController {

    @Autowired
    UserProvider userProvider;

    @PostMapping(value = "/registration", consumes = "application/json", produces = "application/json")
    public ResponseEntity<String> registration(@RequestBody RegistrationData user) {
        HashMap<Boolean, String> success = userProvider.registration(user);
        if (success.containsKey(true)) {
            return ResponseEntity.ok(success.get(true));
        } else {
            return ResponseEntity.badRequest().body(success.get(false));
        }
    }

    @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
    public Map<Object, Object> login(@RequestBody UserCredentials user) {
        return userProvider.login(user);
    }

}
