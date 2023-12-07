package com.microservice.membership.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseTemplate implements Serializable {
    private static final long serialVersionUID = 8672789265930821829L;

    private Integer responseCode;
    private Boolean isSuccess;
    private String message;
    private Object payload;
    private String token;
}
