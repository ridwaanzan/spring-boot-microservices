package com.microservice.membership.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FitnessPackageDto implements Serializable {
    private static final long serialVersionUID = -3631402990971332377L;

    private String name;
    private Long price;
    private Integer duration;
    private List<FitnessPackageDetailDto> fitnessPackageDetail;
}
