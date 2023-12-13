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
public class FitnessPackageDetailDto implements Serializable {
    private static final long serialVersionUID = 6018470884824679082L;

    private Long id;
    private String name;
    private String description;
    private Integer exercise_time;
}
