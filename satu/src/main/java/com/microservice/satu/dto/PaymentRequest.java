package com.microservice.satu.dto;

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
public class PaymentRequest implements Serializable {
    private static final long serialVersionUID = -5258217357574989836L;

    private String cardNumber;
    private String cvvNumber;
    private String amount;
    private String cardHolderName;
    private String expiredDate;
}
