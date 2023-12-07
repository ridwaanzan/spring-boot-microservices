package com.microservice.membership.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmResetPasswordOtpRequestDto implements Serializable {
    private static final long serialVersionUID = 4461903811856758715L;

    private String email;
    private Integer otp;
    private String password;
    private String confirmPassword;
}
