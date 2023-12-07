package com.microservice.membership.controller;

import com.microservice.membership.dto.ConfirmEmailOtpRequestDto;
import com.microservice.membership.dto.LoginUserRequestDto;
import com.microservice.membership.dto.RegisterUserRequestDto;
import com.microservice.membership.dto.ResponseTemplate;
import com.microservice.membership.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseTemplate RegisterUser(@RequestBody RegisterUserRequestDto request) {
        return userService.RegisterUser(request);
    }

    @PostMapping("/confirm-registration")
    public ResponseTemplate ConfirmRegistrationOtp(@RequestBody ConfirmEmailOtpRequestDto request) {
        return userService.ConfirmRegistrationOtp(request);
    }

    @PostMapping("/login")
    public ResponseTemplate LoginUser(@RequestBody LoginUserRequestDto request) {
        return userService.LoginUser(request);
    }

    //FORGOT PASSWORD
    // CONFIRM FORGOT PASSWORD
}
