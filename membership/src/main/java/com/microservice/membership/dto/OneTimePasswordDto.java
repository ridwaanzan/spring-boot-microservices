package com.microservice.membership.dto;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

public class OneTimePasswordDto implements Serializable {
    private static final long serialVersionUID = 8228132859260309241L;

    private Long userId;
    private String otp;
    private Timestamp expiredDate;
    private String type;
}
