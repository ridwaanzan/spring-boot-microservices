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
public class ConfirmEmailOtpRequestDto implements Serializable {
    private static final long serialVersionUID = 7589718685319045875L;

    private String email;
    private Integer otp;
}
