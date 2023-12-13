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
public class ResponseTemplate  implements Serializable {
    private static final long serialVersionUID = 3960641849149122903L;

    private Integer responseCode;
    private Boolean isSuccess;
    private String message;
    private Object payload;
    private String token;
}
