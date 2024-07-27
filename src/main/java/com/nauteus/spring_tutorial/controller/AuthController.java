package com.nauteus.spring_tutorial.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;

import com.nauteus.spring_tutorial.domain.requests.UserRequest;
import com.nauteus.spring_tutorial.domain.responses.RestResponse;
import com.nauteus.spring_tutorial.domain.services.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping(path = "/api/auth")
public class AuthController {

    @Autowired
    public UserService userService;

    @PostMapping(path = "/register/email", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public RestResponse<String> registerWithEmail(@RequestBody UserRequest.RegisterWithEmailRequest request) {
        userService.registerWithEmail(request);
        return RestResponse.<String>builder().data("OK").status(HttpStatus.OK.value()).build();
    }

}
