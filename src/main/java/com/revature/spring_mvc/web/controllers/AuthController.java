package com.revature.spring_mvc.web.controllers;


import com.revature.spring_mvc.models.User;
import com.revature.spring_mvc.services.UserService;
import com.revature.spring_mvc.web.dtos.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService){
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void authenticate(@RequestBody LoginRequest loginRequest, HttpSession httpSession){
        User authUser = userService.authenticateUser(loginRequest.getUsername(), loginRequest.getPassword());
        httpSession.setAttribute("authUser", authUser);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void invalidateSession(HttpSession session){
        session.invalidate();
    }

}
