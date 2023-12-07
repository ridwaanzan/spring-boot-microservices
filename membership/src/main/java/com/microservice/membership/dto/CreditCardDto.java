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
public class CreditCardDto implements Serializable {
    private static final long serialVersionUID = -3597549804573602909L;

    private String cardNumber;
    private String cvvNumber;
    private String amount;
    private String cardHolderName;
    private String expiredDate;
}
