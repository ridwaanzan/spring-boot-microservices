package com.microservice.membership.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "one_time_passwords")
public class OneTimePasswords implements Serializable {
    private static final long serialVersionUID = -2293330424802469672L;

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @Column(name = "otp")
    private Integer otp;

    @Column(name = "expired_date")
    private Timestamp expiredDate;

    @Column(name = "type")
    private String type;
}
