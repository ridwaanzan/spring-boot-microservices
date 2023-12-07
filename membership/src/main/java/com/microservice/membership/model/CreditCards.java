package com.microservice.membership.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "credit_cards")
public class CreditCards implements Serializable {
    private static final long serialVersionUID = -6775213906716648777L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "cvv_number")
    private String cvvNumber;

    @Column(name = "expired_date")
    private String expiredDate;

    @Column(name = "card_holder_name")
    private String cardHolderName;
}
