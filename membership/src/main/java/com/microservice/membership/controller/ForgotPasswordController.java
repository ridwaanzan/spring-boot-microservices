package com.microservice.membership.controller;

import com.microservice.membership.dto.ConfirmResetPasswordOtpRequestDto;
import com.microservice.membership.dto.ResetPasswordRequestDto;
import com.microservice.membership.dto.ResponseTemplate;
import com.microservice.membership.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgotPasswordController {
    @Autowired
    private ForgotPasswordService forgotPasswordService;

    @PostMapping("/reset-password")
    public ResponseTemplate ResetPassword(@RequestBody ResetPasswordRequestDto request) {
        return forgotPasswordService.ResetPassword(request);
    }

    @PostMapping("/confirm-reset-otp")
    public ResponseTemplate ConfirmResetPasswordOtp(@RequestBody ConfirmResetPasswordOtpRequestDto request) {
        return forgotPasswordService.ConfirmResetPasswordOtp(request);
    }
}
